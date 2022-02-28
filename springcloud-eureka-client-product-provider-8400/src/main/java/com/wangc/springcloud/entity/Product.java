package com.wangc.springcloud.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Product {

    // 自增id
    private Long id;
    // 产品名称
    private String name;
    // 库存
    private Integer stock;
}
