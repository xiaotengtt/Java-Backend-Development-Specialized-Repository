package com.xiaoteng.data.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class Payment {
    
    /**
     * 支付ID
     */
    private Long id;
    
    /**
     * 订单ID
     */
    private Long orderId;
    
    /**
     * 支付单号
     */
    private String payNo;
    
    /**
     * 支付方式 1支付宝 2微信 3余额
     */
    private Integer paymentType;
    
    /**
     * 第三方交易号
     */
    private String transactionNo;
    
    /**
     * 支付金额
     */
    private BigDecimal payAmount;
    
    /**
     * 支付状态 0待支付 1支付成功 2支付失败 3已退款
     */
    private Integer status;
    
    /**
     * 支付时间
     */
    private LocalDateTime payTime;
    
    /**
     * 创建时间
     */
    private LocalDateTime createTime;
    
    /**
     * 更新时间
     */
    private LocalDateTime updateTime;
}
