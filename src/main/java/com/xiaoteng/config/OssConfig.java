package com.xiaoteng.config;

import com.aliyun.sdk.service.oss2.OSSClient;
import com.aliyun.sdk.service.oss2.credentials.CredentialsProvider;
import com.aliyun.sdk.service.oss2.credentials.StaticCredentialsProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration

/*
 * 客户端初始化类
 * 客户端初始化的结果当然是得到一个可以直接使用的OssClient
 *
 * 那么我们都需配置什么呢？
 *
 *
 */

public class OssConfig {
    
    @Bean
    public OSSClient ossClient(OssProperties ossProperties) {
        CredentialsProvider credentialsProvider =
                new StaticCredentialsProvider(
                        ossProperties.getAccessKeyId(),
                        ossProperties.getAccessKeySecret()
                );
        
        return OSSClient.newBuilder()
                .credentialsProvider(credentialsProvider)
                .region(ossProperties.getRegion())
                .build();
    }
}
