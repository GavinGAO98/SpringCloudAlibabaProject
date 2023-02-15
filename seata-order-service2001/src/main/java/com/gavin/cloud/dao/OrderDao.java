package com.gavin.cloud.dao;

import com.gavin.cloud.domain.Order;

/**
 * 数据操作层包含两个操作：新建订单和支付后修改订单状态
 */
@Mapper //Q：明明在父pom中引入了mybatis包，为什么还会标红？
public interface OrderDao {

    void createNewOrder(Order order);

    void updateOrderStatus(@Param("userId")Long userId, @Param("status") Integer status);
}
