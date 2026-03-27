package com.xiaoteng.server.service.impl;

import com.aliyun.sdk.service.oss2.OSSClient;
import com.aliyun.sdk.service.oss2.models.*;
import com.aliyun.sdk.service.oss2.transport.BinaryData;
import com.xiaoteng.config.OssProperties;
import com.xiaoteng.server.service.OssFileStorageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.time.LocalDate;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
/*
 * 一个专门和OSS打交道的类
 * 删除 OSS 文件
 * 上传 OSS 文件
 * 访问 OSS 文件
 * 下载 OSS 文件
 * 生成签名 URL
 */
public class OssFileStorageServiceImpl implements OssFileStorageService {
    
    private final OSSClient ossClient;
    private final OssProperties ossProperties;
    
    /*
     * 上传文件操作
     *
     * 此处返回值应该是
     *
     */
    @Override
    public String uploadFile(MultipartFile file) {
        try {
            String originalFilename = file.getOriginalFilename();
            
            String suffix = "";
            if (originalFilename != null && originalFilename.contains(".")) {
                suffix = originalFilename.substring(originalFilename.lastIndexOf("."));
            }
            
            LocalDate now = LocalDate.now();
            String dir = now.getYear() + "/" + now.getMonthValue() + "/" + now.getDayOfMonth();
            
            String objectName = dir + "/" + UUID.randomUUID() + suffix;
            
            
            //构造文件上传的对象
            PutObjectRequest request = PutObjectRequest.newBuilder()
                    .bucket(ossProperties.getBucketName())
                    .key(objectName)
                    .body(BinaryData.fromBytes(file.getBytes()))
                    .build();
            
            PutObjectResult result = ossClient.putObject(request);
            
            log.info("文件上传成功，bucket={}, objectKey={}, requestId={}",
                    ossProperties.getBucketName(), objectName, result.requestId());
            
            return objectName;  //返回值不重要
            
        } catch (Exception e) {
            log.error("文件上传失败", e);
            throw new RuntimeException("文件上传失败", e);
        }
    }
    
    /*
     * 删除文件
     */
    @Override
    public void deleteFile(String objectName) {
        try {
            DeleteObjectRequest request = DeleteObjectRequest.newBuilder()
                    .bucket(ossProperties.getBucketName())
                    .key(objectName)
                    .build();
            
            DeleteObjectResult result = ossClient.deleteObject(request);
            
            log.info("文件删除成功，bucket={}, objectKey={}, requestId={}",
                    ossProperties.getBucketName(), objectName, result.requestId());
        } catch (Exception e) {
            log.error("文件删除失败，objectKey={}", objectName, e);
            throw new RuntimeException("文件删除失败", e);
        }
    }
    
    /*
     * 访问文件操作
     */
    @Override
    public String getFileUrl(String objectName) {
        HeadObjectRequest request = HeadObjectRequest.newBuilder()
                .bucket(ossProperties.getBucketName())
                .key(objectName)
                .build();
        HeadObjectResult result = ossClient.headObject(request);
        return result.eTag();
    }
    
    /*
     * 下载 OSS 文件
     */
    @Override
    public InputStream downloadFile(String objectName) {
        try {
            GetObjectRequest request = GetObjectRequest.newBuilder()
                    .bucket(ossProperties.getBucketName())
                    .key(objectName)
                    .build();
            
            GetObjectResult result = ossClient.getObject(request);
            
            log.info("文件下载成功，bucket={}, objectKey={}, requestId={}",
                    ossProperties.getBucketName(), objectName, result.requestId());
            
            return result.body();
        } catch (Exception e) {
            log.error("文件下载失败，objectKey={}", objectName, e);
            throw new RuntimeException("文件下载失败", e);
        }
    }
    
    /*
     * 生成签名 URL
     * 你现在先返回可访问地址；如果后面要做私有桶签名，再单独补 presign 版本
     */
    @Override
    public String generatePresignedUrl(String objectKey) {
        return getFileUrl(objectKey);
    }
}
