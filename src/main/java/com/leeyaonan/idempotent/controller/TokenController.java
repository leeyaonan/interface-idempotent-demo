package com.leeyaonan.idempotent.controller;

import com.leeyaonan.idempotent.service.TokenService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Rot
 * @date 2020/4/26 17:00
 */
@RestController
@Slf4j
@AllArgsConstructor
public class TokenController {

    public final TokenService tokenService;

    /**
     * 前端获取Token
     * @param expireTime
     * @return
     */
    @GetMapping("/token")
    public String getToken(@RequestParam(required = false) Long expireTime) {
        return tokenService.createToken(expireTime);
    }
}

