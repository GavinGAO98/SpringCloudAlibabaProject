package com.gavin.cloud.service;

import com.gavin.cloud.domain.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;

/**
 * 操作用户账户
 */
@FeignClient(value = "seata-account-service")
public interface AccountService {
    @PostMapping(value = "/account/decrease")
    CommonResult decreaseMoney(@RequestParam("userId") Long userId, @RequestParam("money") BigDecimal money);
}
