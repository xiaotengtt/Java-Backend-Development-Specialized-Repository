package com.xiaoteng.server.service;


import com.xiaoteng.data.vo.FileUploadVO;
import org.springframework.web.multipart.MultipartFile;

public interface FileService {
    
    FileUploadVO upload(MultipartFile file, String bizType, Long bizId, Long createdBy);
}
