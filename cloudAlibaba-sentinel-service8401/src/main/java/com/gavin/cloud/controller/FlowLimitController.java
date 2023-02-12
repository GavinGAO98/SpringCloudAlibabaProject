package com.gavin.cloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
@Slf4j
public class FlowLimitController {

    @GetMapping("/testA")
    public String testA(){
        //测试线程数模式进行流控
        try {
            //Thread.sleep(800);
            TimeUnit.MILLISECONDS.sleep(800);
        }catch(InterruptedException e){
            e.printStackTrace();
        }
        return "-----testA";
    }

    @GetMapping("/testB")
    public String testB(){
        log.info(Thread.currentThread().getName() + "\t testB" ); //Q：为什么日志没有打印到控制台？
        return "-----testB";
    }

    @GetMapping("/testHotKey")
    @SentinelResource(value = "testHotKey", blockHandler = "hostKeyHandler") //当资源名为testHotKey被热点xx时，调用HostKeyHandler方法
    //@SentinelResource()
    public String testHotKey(@RequestParam(value = "p1", required = false) String p1,
                             @RequestParam(value = "p2", required = false) String p2){
        return "------testHotKey";
    }

    /**
     * 服务降级的兜底方法
     * @param p1 参数列表必须要和原方法相同
     * @param p2
     * @param exception 参数意外项，即提供了额外的参数
     * @return
     */
    public String hostKeyHandler(String p1, String p2, BlockException exception){
        return "handle host key"; //否则默认显示Sentinel的系统信息： blocked by sentinel(flow limiting)

    }
}
