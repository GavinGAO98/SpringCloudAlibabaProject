package com.gavin.cloud.service;

import com.gavin.cloud.entities.CommonResult;
import com.gavin.cloud.entities.Payment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient(value = "nacos-provider-payment", fallback = PaymentFallbackService.class)
public interface PaymentService {

    @GetMapping(value = "/paymentSQL/{id}") //Q：这个注解有没有用？
    public CommonResult<Payment> paymentSQL(@PathVariable("id")Long id);
}
