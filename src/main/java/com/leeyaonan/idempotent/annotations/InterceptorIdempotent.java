package com.leeyaonan.idempotent.annotations;

import java.lang.annotation.*;

/**
 * 用拦截器实现接口幂等性的对应注解
 * @author Rot
 * @date 2020/4/26 15:01
 */
@Documented
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface InterceptorIdempotent {

}
