package com.xiaoteng.data.entity;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserAddress {
    
    /**
     * 地址ID
     */
    private Long id;
    
    /**
     * 用户ID
     */
    private Long userId;
    
    /**
     * 收货人姓名
     */
    private String receiverName;
    
    /**
     * 收货人手机号
     */
    private String receiverPhone;
    
    /**
     * 省
     */
    private String province;
    
    /**
     * 市
     */
    private String city;
    
    /**
     * 区/县
     */
    private String district;
    
    /**
     * 详细地址
     */
    private String detailAddress;
    
    /**
     * 是否默认地址 0否 1是
     */
    private Integer isDefault;
    
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
