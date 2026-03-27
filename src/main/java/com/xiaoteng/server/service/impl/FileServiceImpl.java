package com.xiaoteng.server.service.impl;

import com.xiaoteng.config.OssProperties;
import com.xiaoteng.context.UserContext;
import com.xiaoteng.data.dto.FileMetadataCreateDTO;
import com.xiaoteng.data.entity.FileMetadata;
import com.xiaoteng.data.vo.FileUploadVO;
import com.xiaoteng.server.mapper.FileMetadataMapper;
import com.xiaoteng.server.service.FileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@Slf4j
/*
 * 负责文件上传、文件合法性判断、元信息的保存和返回的类
 */

public class FileServiceImpl implements FileService {
    
    
    private final FileMetadataMapper fileMetadataMapper;
    private final OssFileStorageServiceImpl ossFileStorageServiceImpl;
    private final OssProperties ossProperties;
    
    
    
    @Autowired
    public FileServiceImpl(FileMetadataMapper fileMetadataMapper,
                           OssFileStorageServiceImpl ossFileStorageServiceImpl,
                           OssProperties ossProperties) {
        this.fileMetadataMapper = fileMetadataMapper;
        this.ossFileStorageServiceImpl = ossFileStorageServiceImpl;
        this.ossProperties = ossProperties;
    }
    
    /*
    文件上传操作
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public FileUploadVO upload(MultipartFile file, String bizType, Long bizId, Long createdBy){
        //文件校验部分
        if (file == null || file.isEmpty()) {
            throw new RuntimeException("上传文件不能为空");
        }
        //Oss客户端上传文件
        String objectName = ossFileStorageServiceImpl.uploadFile(file);
        
        //实现DTO的填充，进而实现数据库表的写入
        FileMetadataCreateDTO fileMetadataCreateDTO = new FileMetadataCreateDTO();
        fileMetadataCreateDTO.setOriginalName(file.getOriginalFilename());
        fileMetadataCreateDTO.setBucketName(ossProperties.getBucketName());
        fileMetadataCreateDTO.setSize(file.getSize());
        fileMetadataCreateDTO.setObjectKey(objectName);
        fileMetadataCreateDTO.setAccessUrl(objectName);
        fileMetadataCreateDTO.setCreatedBy(UserContext.getUserId());
        
        //文件后缀截取
        String contentType=file.getContentType();
        if (contentType != null) {
            String ext=contentType.substring(file.getContentType().lastIndexOf("/") + 1);
            fileMetadataCreateDTO.setExt(ext);
        }
        
        if(bizType !=null && !bizType.isEmpty()){
            fileMetadataCreateDTO.setBizType(bizType);
        }
        if (bizId != null && bizId != 0) {
            fileMetadataCreateDTO.setBizId(bizId);
        }
        if (createdBy != null && createdBy != 0) {
            fileMetadataCreateDTO.setCreatedBy(createdBy);
        }
        
        FileMetadata fileMetadata = new FileMetadata();
        BeanUtils.copyProperties(fileMetadataCreateDTO,fileMetadata);
        fileMetadataMapper.insert(fileMetadata);
        
        FileUploadVO fileUploadVO = new FileUploadVO();
        fileUploadVO.setFileId(fileMetadata.getId());
        fileUploadVO.setOriginalName(fileMetadata.getOriginalName());
        fileUploadVO.setObjectKey(fileMetadata.getObjectKey());
        fileUploadVO.setAccessUrl(fileMetadata.getAccessUrl());
        fileUploadVO.setSize(fileMetadata.getSize());
        fileUploadVO.setContentType(fileMetadata.getContentType());
        
        return fileUploadVO;
        
    }
    
    
}
