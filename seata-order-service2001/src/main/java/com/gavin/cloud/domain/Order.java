package com.gavin.cloud.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * 订单对应的实体类
 * 注意每个属性与表的字段格式需要映射关系
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    private Long id;
    private Long userId;
    private Long productId;
    private Integer count; //Q：为什么都用包装类型，而不用基本类型？

    private BigDecimal money;
    private Integer status; // 0表示订单未完成，1表示已完成
}
