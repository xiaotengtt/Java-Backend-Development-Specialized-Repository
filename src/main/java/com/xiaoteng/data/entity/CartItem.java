package com.xiaoteng.data.entity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CartItem {
    
    /**
     * 购物车ID
     */
    private Long id;
    
    /**
     * 用户ID
     */
    private Long userId;
    
    /**
     * 商品ID
     */
    private Long productId;
    
    /**
     * 购买数量
     */
    private Integer quantity;
    
    /**
     * 是否选中 1选中 0未选中
     */
    private Integer checked;
    
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
