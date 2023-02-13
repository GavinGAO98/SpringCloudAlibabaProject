package com.gavin.cloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.gavin.cloud.entities.CommonResult;
import com.gavin.cloud.entities.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
@Slf4j
public class CircuitBreakerController {
    public static final String SERVICE_URL = "http://nacos-provider-payment";

    @Resource
    private RestTemplate restTemplate;

    @RequestMapping("/consumer/fail/{id}")
    //@SentinelResource(value = "fail") //什么都不配置，只会向前端返回一个默认的error page
    //@SentinelResource(value = "fail", fallback = "exceptionHandler") //fallback属性配置Java异常时的兜底方法
    //@SentinelResource(value = "fail", blockHandler = "flowLimitHandler")
    @SentinelResource(value = "fail",
            fallback = "exceptionHandler",
            blockHandler = "flowLimitHandler", //两个同时配置出现Java异常时，也会走blockHandler
            exceptionsToIgnore = IllegalArgumentException.class)  //当抛出IllegalArgumentException时并不会启动服务降级，而是直接在前台显示error page
    public CommonResult<Payment> fail(@PathVariable("id") Long id){
        CommonResult<Payment> result = restTemplate.getForObject(SERVICE_URL + "/paymentSQL/" + id, CommonResult.class, id);

        if(id == 4){
            throw new IllegalArgumentException("IllegalArgumentException, 非法参数异常.....");
        }else if(result.getData() == null){
            throw new NullPointerException("NullPointerException, 该ID没有对应的记录");
        }

        return result;
    }

    /**
     *
     * @param id 注解可以不加括号
     * @param e
     * @return
     */
    public CommonResult exceptionHandler(@PathVariable Long id, Throwable e){
        Payment payment = new Payment(id,"null");
        return new CommonResult<>(444, "处理异常的兜底方法exceptionHandler。 该异常内容为 " + e.getMessage(), payment);
    }

    public CommonResult flowLimitHandler(@PathVariable Long id, BlockException blockException){
        Payment payment = new Payment(id,"null");
        return new CommonResult<>(445, "由Sentinel设置的限流规则导致无此流水。 该异常内容为 " + blockException.getMessage(), payment);
    }
}
