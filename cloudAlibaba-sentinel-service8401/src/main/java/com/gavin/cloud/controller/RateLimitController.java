package com.gavin.cloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.gavin.cloud.entities.CommonResult;
import com.gavin.cloud.entities.Payment;
import com.gavin.cloud.myHandler.CustomerBlockHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RateLimitController {

    @GetMapping("/byResource")
    @SentinelResource(value = "byResource", blockHandler = "resourceExceptionHandler")
    public CommonResult byResource(){
        return new CommonResult(200, "按资源名称限流 pass", new Payment(2023L, "serial001"));
    }

    /**
     * 服务降级的兜底方法.
     * 缺点：兜底方法与控制器耦合，每一个控制器方法都要有一个兜底方法
     * @param ex
     * @return
     */
    public CommonResult resourceExceptionHandler(BlockException ex){
        return new CommonResult(444, ex.getClass().getCanonicalName() + "\t 服务不可用");
    }

    @GetMapping("/rateLimit/byUrl") //Q：为什么按URL限流不生效？
    @SentinelResource(value = "byUrl") //返回系统自动的限流结果
    public CommonResult byUrl(){
        return new CommonResult(200, "按url限流测试oK", new Payment(2023L, "serial002"));
    }

    @GetMapping("/rateLimit/custom")
    @SentinelResource(value = "custom", blockHandlerClass = CustomerBlockHandler.class, blockHandler = "handleCustomer1") //返回系统自动的限流结果
    public CommonResult byCustom(){
        return new CommonResult(200, "按客户自定义限流测试oK", new Payment(2023L, "serial003"));
    }
}
