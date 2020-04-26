package com.leeyaonan.idempotent.annotations;

import java.lang.annotation.*;

/**
 * 使用AOP实现接口幂等性的注解
 * @author Rot
 * @date 2020/4/26 17:58
 */
@Documented
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface AopIdempotent {
}
