package com.xiaoteng.data.dto;


import lombok.Data;

/**
 * 文件元信息新增DTO
 * 用于Service层向Mapper层传递入库参数
 */
@Data
public class FileMetadataCreateDTO {
    
    /**
     * 原始文件名，例如：avatar.png
     */
    private String originalName;
    
    /**
     * 存储文件名，例如：550e8400-e29b-41d4-a716-446655440000.png
     */
    private String storedName;
    
    /**
     * 文件后缀，例如：png、jpg、pdf
     */
    private String ext;
    
    /**
     * 文件MIME类型，例如：image/png、application/pdf
     */
    private String contentType;
    
    /**
     * 文件大小，单位：字节
     */
    private Long size;
    
    /**
     * 存储类型，例如：OSS
     */
    private String storageType;
    
    /**
     * Bucket名称
     */
    private String bucketName;
    
    /**
     * OSS对象Key，例如：avatar/2026/03/26/uuid.png
     */
    private String objectKey;
    
    /**
     * 文件访问地址
     */
    private String accessUrl;
    
    /**
     * 业务类型，例如：USER_AVATAR、CONTRACT
     */
    private String bizType;
    
    /**
     * 业务主键ID，例如：用户ID、合同ID
     */
    private Long bizId;
    
    /**
     * 上传人ID
     */
    private Long createdBy;
}
