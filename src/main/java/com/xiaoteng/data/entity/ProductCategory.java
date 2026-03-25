package com.xiaoteng.data.entity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ProductCategory {
    
    /**
     * 分类ID
     */
    private Long id;
    
    /**
     * 分类名称
     */
    private String name;
    
    /**
     * 父分类ID，0为一级分类
     */
    private Long parentId;
    
    /**
     * 排序值
     */
    private Integer sort;
    
    /**
     * 状态 1启用 0禁用
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
