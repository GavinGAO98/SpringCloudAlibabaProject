package com.gavin.cloud.service.impl;

import com.gavin.cloud.dao.OrderDao;
import com.gavin.cloud.domain.Order;
import com.gavin.cloud.service.AccountService;
import com.gavin.cloud.service.OrderService;
import com.gavin.cloud.service.StorageService;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    //@Autowired
    @Resource
    private OrderDao orderDao; //由mybatis创建dao接口实现类并注入bean

    //调用的其他微服务
    @Resource //Q：这个自动注入由feign实现的吗？
    private StorageService storageService;

    @Resource
    private AccountService accountService;

    /**
     * 从业务逻辑来看，创建订单不一定能支付成功，所以订单状态为0
     * 下订单的四个步骤：生成订单、扣减库存、扣账号、修改订单的状态
     *
     * @param order
     */
    @Override
    @GlobalTransactional(name = "gavin-global-tx", rollbackFor=Error.class) //全局事务控制
    public void createOrder(Order order) {

        //Step1：生成订单
        log.info("-------->开始创建订单");
        orderDao.createNewOrder(order);

        //Step2：扣减库存
        log.info("-------->订单微服务开始调用库存服务扣减库存");
        storageService.decreaseStock(order.getProductId(), order.getCount());
        log.info("-------->调用库存服务扣减库存完成");

        //Step3：扣减钱
        log.info("-------->订单微服务调用账户服务，扣减money");
        accountService.decreaseMoney(order.getUserId(), order.getMoney());
        log.info("-------->调用账户服务扣减money完成");

        //Step4: 修改订单状态
        log.info("-------->修改订单状态开始");
        orderDao.updateOrderStatus(order.getUserId(), 0); //Q；为什么修改为0？
        log.info("-------->修改订单状态成功");

        log.info("-------->下单成功");
    }
}
