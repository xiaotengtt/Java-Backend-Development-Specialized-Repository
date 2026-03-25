package com.xiaoteng.server.mapper;


import com.github.pagehelper.Page;
import com.xiaoteng.data.dto.ProductQueryDTO;
import com.xiaoteng.data.vo.ProductVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ProductMapper {
    
     Page<ProductVO> pageQuery(ProductQueryDTO productQueryDTO);
}
