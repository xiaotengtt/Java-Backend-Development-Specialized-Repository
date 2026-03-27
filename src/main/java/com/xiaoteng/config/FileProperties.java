package com.xiaoteng.config;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "file")
public class FileProperties {
    
    private PathConfig path;
    
    @Data
    public static class PathConfig {
        private String prefix;
        private Boolean useDatePath;
    }
}
