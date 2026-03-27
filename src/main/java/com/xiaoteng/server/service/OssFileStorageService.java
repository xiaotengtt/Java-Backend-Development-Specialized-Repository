package com.xiaoteng.server.service;

import com.xiaoteng.data.vo.FileUploadVO;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

public interface OssFileStorageService {
    
    String uploadFile(MultipartFile file);
    
    void deleteFile(String objectKey);
    
    String getFileUrl(String objectKey);
    
    InputStream downloadFile(String objectKey);
    
    String generatePresignedUrl(String objectKey);
}
