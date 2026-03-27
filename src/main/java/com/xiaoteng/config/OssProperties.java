package com.xiaoteng.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "aliyun.oss")
/*
 * 客户端配置类
 */
public class OssProperties {
    
    private String endpoint;
    
    private String region;
    
    private String bucketName;
    
    private String urlPrefix;
    
    private String accessKeyId;
    
    private String accessKeySecret;
}
