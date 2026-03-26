package com.xiaoteng.data.dto;


import lombok.Data;
import java.io.Serializable;

/**
 * 用户地址返回数据传递对象
 */
@Data
public class UserAddressDTO implements Serializable {
    
    /**
     * 地址ID
     */
    private Long id;
    
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
     * 完整地址
     */
    private String fullAddress;
    
    /**
     * 是否默认地址：0-否，1-是
     */
    private Integer isDefault;
}
