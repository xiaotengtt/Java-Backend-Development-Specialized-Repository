package com.xiaoteng.server.mapper;

import com.xiaoteng.data.entity.FileMetadata;
import com.xiaoteng.data.vo.FileUploadVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FileMetadataMapper {
    
    
    void insert(FileMetadata fileMetadata);
}
