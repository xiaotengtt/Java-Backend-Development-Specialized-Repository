package com.xiaoteng.utils;

import com.xiaoteng.config.FileProperties;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.UUID;

@Component
public class FilePathGenerator {
    
    private final FileProperties fileProperties;
    
    public FilePathGenerator(FileProperties fileProperties) {
        this.fileProperties = fileProperties;
    }
    
    public String generate(String originalFilename) {
        String ext = getExtension(originalFilename);
        String prefix = fileProperties.getPath().getPrefix();
        Boolean useDatePath = fileProperties.getPath().getUseDatePath();
        String uuid = UUID.randomUUID().toString().replace("-", "");
        
        if (Boolean.TRUE.equals(useDatePath)) {
            LocalDate now = LocalDate.now();
            return String.format("%s/%d/%02d/%02d/%s%s",
                    prefix,
                    now.getYear(),
                    now.getMonthValue(),
                    now.getDayOfMonth(),
                    uuid,
                    ext.isEmpty() ? "" : "." + ext);
        }
        
        return String.format("%s/%s%s",
                prefix,
                uuid,
                ext.isEmpty() ? "" : "." + ext);
    }
    
    public String getExtension(String fileName) {
        if (fileName == null || !fileName.contains(".")) {
            return "";
        }
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }
    
    public String getStoredName(String objectKey) {
        if (objectKey == null || !objectKey.contains("/")) {
            return objectKey;
        }
        return objectKey.substring(objectKey.lastIndexOf("/") + 1);
    }
}
