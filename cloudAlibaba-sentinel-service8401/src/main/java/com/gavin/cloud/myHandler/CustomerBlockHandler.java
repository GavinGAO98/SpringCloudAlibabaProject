package com.gavin.cloud.myHandler;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.gavin.cloud.entities.CommonResult;

/**
 * 自定义通用的兜底方法
 */
public class CustomerBlockHandler {

    public static CommonResult handleCustomer1(BlockException ex){
        return new CommonResult(4444, "按自定义方法处理全局 handlerException");
    }

    public static CommonResult handleCustomer2(BlockException ex){
        return new CommonResult(4444, "按自定义方法处理global handlerException");
    }

}
