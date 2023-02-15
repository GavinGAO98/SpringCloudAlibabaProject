package com.gavin.cloud.service;


import com.gavin.cloud.domain.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 订单微服务调用的是库存微服务【通过feign实现】
 */
@FeignClient(value = "seata-storage-service")
public interface StorageService {

    /**
     *
     * @param productId 订购的商品id
     * @param count 订单中某件商品的订购数量
     * @return
     */
    @PostMapping(value = "/storage/decrease")
    CommonResult decreaseStock(@RequestParam("productId") Long productId, @RequestParam("count") Integer count);


}
