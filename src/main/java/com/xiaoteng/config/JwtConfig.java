package com.xiaoteng.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Data
@ConfigurationProperties(prefix = "jwt")
public class JwtConfig {
    private String secret;
    private long expiration;
    private long refreshExpiration;
    private String header;
    private String prefix;
    private String issuer;
    private String subject;
    private String algorithm;
    private long clockSkew;
    
    // getter & setter
}
