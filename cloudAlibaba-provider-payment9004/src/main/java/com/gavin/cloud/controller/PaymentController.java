package com.gavin.cloud.controller;

import com.gavin.cloud.entities.CommonResult;
import com.gavin.cloud.entities.Payment;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
public class PaymentController {
    @Value("${server.port}")
    private String serverPort;

    public static HashMap<Long, Payment> map = new HashMap<>();

    //静态代码块
    static{
        map.put(1L, new Payment(1L, "28a8c1e3bc2742d8848569891fb42181"));
        map.put(2L, new Payment(2L, "bba8c1e3bc2742d8848569891ac32182"));
        map.put(3L, new Payment(3L, "6ua8c1e3bc2742d8848569891xt92183"));
    }

    @GetMapping(value = "/paymentSQL/{id}")
    public CommonResult<Payment> getPaymentPort(@PathVariable("id") Integer id){
        Payment payment = map.get(id);
        CommonResult<Payment> result = new CommonResult<>(200, "From database, serverPort: " + serverPort, payment);
        return result;
    }
}