package com.xiaoteng.server.service;

import com.github.pagehelper.Page;
import com.xiaoteng.data.dto.ProductQueryDTO;
import com.xiaoteng.data.vo.ProductVO;

public interface ProductService {
    
    
    Page<ProductVO> productPageQuery(ProductQueryDTO productQueryDTO);
}
