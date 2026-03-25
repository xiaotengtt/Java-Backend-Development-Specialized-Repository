package com.xiaoteng.data.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class Product {
    
    /**
     * 商品ID
     */
    private Long id;
    
    /**
     * 分类ID
     */
    private Long categoryId;
    
    /**
     * 商品名称
     */
    private String name;
    
    /**
     * 商品副标题
     */
    private String subTitle;
    
    /**
     * 销售价格
     */
    private BigDecimal price;
    
    /**
     * 库存
     */
    private Integer stock;
    
    /**
     * 商品主图
     */
    private String coverImage;
    
    /**
     * 商品描述
     */
    private String description;
    
    /**
     * 状态 1上架 0下架
     */
    private Integer status;
    
    /**
     * 逻辑删除 0未删除 1已删除
     */
    private Integer deleted;
    
    /**
     * 创建时间
     */
    private LocalDateTime createTime;
    
    /**
     * 更新时间
     */
    private LocalDateTime updateTime;
}
