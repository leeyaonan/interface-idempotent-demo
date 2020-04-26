package com.leeyaonan.idempotent.controller;

import com.leeyaonan.idempotent.annotations.InterceptorIdempotent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Rot
 * @date 2020/4/26 17:25
 */
@RestController
@Slf4j
public class TestController {

    /**
     * 测试用拦截器实现的接口幂等性
     * @return
     */
    @GetMapping("/test")
    @InterceptorIdempotent
    public String testIdemPotent() {
        return "Hello World!";
    }
}
