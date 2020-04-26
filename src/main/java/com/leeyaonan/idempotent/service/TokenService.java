package com.leeyaonan.idempotent.service;

import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Rot
 * @date 2020/4/26 17:03
 */
public interface TokenService {

    /**
     * 获取Token
     * @param expireTime 失效时间
     * @return token
     */
    public String createToken(Long expireTime);

    boolean checkToken(HttpServletRequest request) throws Exception;
}
