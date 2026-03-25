package com.xiaoteng.data.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductQueryDTO{
    private Integer pageNum;
    private Integer pageSize;
    private String name;
    private Integer status;
}
