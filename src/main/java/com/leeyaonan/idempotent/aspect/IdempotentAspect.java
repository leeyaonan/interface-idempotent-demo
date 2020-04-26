package com.leeyaonan.idempotent.aspect;


import com.leeyaonan.idempotent.service.TokenService;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.ArrayUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * @author Rot
 * @date 2020/4/26 18:03
 */
@Aspect
@Component
@AllArgsConstructor
public class IdempotentAspect {

    private final TokenService tokenService;

    @Pointcut("@annotation(com.leeyaonan.idempotent.annotations.AopIdempotent)")
    private void pointCut() {

    }

    @Around("pointCut()")
    public Object checkToken(ProceedingJoinPoint joinPoint) throws Throwable {
        // 获取请求头、请求参数，从中拿取Token
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

        try {
            boolean checkToken = tokenService.checkToken(request);
            if (checkToken) {
                return joinPoint.proceed();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
