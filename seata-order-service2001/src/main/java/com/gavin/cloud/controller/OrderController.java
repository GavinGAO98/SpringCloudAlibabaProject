package com.gavin.cloud.controller;

import com.gavin.cloud.domain.CommonResult;
import com.gavin.cloud.domain.Order;
import com.gavin.cloud.service.OrderService;
import com.gavin.cloud.service.impl.OrderServiceImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController //Q：为什么还用restcontroller返回json字符串而不是返回页面?
public class OrderController {
    @Resource
    private OrderService orderService;

    @GetMapping("/order/createOrder") //Q：为什么这里用get方法？
    public CommonResult createOrder(Order order){
        orderService.createOrder(order);
        return new CommonResult(200, "订单创建成功");
    }
}
