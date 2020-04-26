package com.leeyaonan.idempotent.config;

import com.leeyaonan.idempotent.config.interceptor.IdemPotentInterceptor;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;


/*
* 2020年4月26日 17点41分更新：将原来的继承WebMvcConfigurerAdapter类改为实现WebMvcConfigurer接口
* 参考：https://segmentfault.com/a/1190000018904390?utm_source=tag-newest
* */


//@Configuration
//@AllArgsConstructor
//public class WebConfiguration extends WebMvcConfigurerAdapter {
//
//    private final IdemPotentInterceptor idemPotentInterceptor;
//
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(idemPotentInterceptor);
//        super.addInterceptors(registry);
//    }
//}


/**
 * @author Rot
 * @date 2020/4/26 17:13
 */
@Configuration
@AllArgsConstructor
public class WebConfiguration implements WebMvcConfigurer {

    private final IdemPotentInterceptor idemPotentInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(idemPotentInterceptor);
    }
}
