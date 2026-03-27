package com.xiaoteng.data.vo;

import lombok.Data;

@Data
public class FileUploadVO {
    
    private Long fileId;
    
    private String originalName;
    
    private String objectKey;
    
    private String accessUrl;
    
    private Long size;
    
    private String contentType;
}
