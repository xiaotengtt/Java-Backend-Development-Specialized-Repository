package com.xiaoteng.server.controller.user;


import com.xiaoteng.data.vo.FileUploadVO;
import com.xiaoteng.result.Result;
import com.xiaoteng.server.service.impl.FileServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/files")
@Slf4j

/*
  上传文件操作接口
 */

public class FileController {

    private final FileServiceImpl fileServiceImpl;
    
    @Autowired
    public FileController(FileServiceImpl fileServiceImpl) {
        this.fileServiceImpl = fileServiceImpl;
    }
    
    
    /**
     * 单文件上传
     *
     * @param file 上传文件
     * @param bizType 业务类型
     * @param bizId 业务主键ID
     * @param createdBy 上传人
     * @return 上传结果
     */
    @PostMapping("/upload")
    public Result<FileUploadVO> upload(@RequestParam("file") MultipartFile file,
                         @RequestParam(value = "bizType", required = false) String bizType,
                         @RequestParam(value = "bizId", required = false) Long bizId,
                         @RequestParam(value = "createdBy", required = false) Long createdBy) {
        return Result.success(fileServiceImpl.upload(file, bizType, bizId, createdBy));
    }
    
    
    /**
     * 多文件上传
     */
    
    @PostMapping("/upload/batch")
    public String uploadBatch(@RequestParam("files") MultipartFile[] files) {
        
        
        return null;
    
    }
    
    
}
