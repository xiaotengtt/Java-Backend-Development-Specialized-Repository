package com.xiaoteng.server.controller.user;


import com.github.pagehelper.Page;
import com.xiaoteng.data.dto.ProductQueryDTO;
import com.xiaoteng.data.entity.Product;
import com.xiaoteng.data.vo.ProductVO;
import com.xiaoteng.result.Result;
import com.xiaoteng.server.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/product")
public class ProductController {
    
    private ProductService productService;
    
    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }
    
    
    //商品的查询，现在商品都是分页展示，所以直接做分页查询，还要结合条件筛选
    @GetMapping("/page")
    public Result<Page<ProductVO>> productPageQuery(ProductQueryDTO productQueryDTO)
    {
        Page<ProductVO> productVOS = productService.productPageQuery(productQueryDTO);
        return Result.success(productVOS);
    }
    
    
    //商品的
    
    
    
    
    
    
    
    
    
    
}
