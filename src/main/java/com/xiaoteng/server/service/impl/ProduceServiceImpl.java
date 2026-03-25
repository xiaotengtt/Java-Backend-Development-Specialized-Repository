package com.xiaoteng.server.service.impl;


import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.xiaoteng.data.dto.ProductQueryDTO;
import com.xiaoteng.data.vo.ProductVO;
import com.xiaoteng.server.mapper.ProductMapper;
import com.xiaoteng.server.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ProduceServiceImpl implements ProductService {
    
    
    
    private final ProductMapper productMapper;
    
    @Autowired
    public ProduceServiceImpl(ProductMapper productMapper) {
        this.productMapper = productMapper;
    }
    
    
    @Override
    public Page<ProductVO> productPageQuery(ProductQueryDTO productQueryDTO) {
        try {
            PageHelper.startPage(productQueryDTO.getPageNum(), productQueryDTO.getPageSize());
        }catch (Exception e){
            log.error(e.getMessage());
        }
        return productMapper.pageQuery(productQueryDTO);
    }
}
