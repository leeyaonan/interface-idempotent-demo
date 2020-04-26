package com.leeyaonan.idempotent.config;

import com.leeyaonan.idempotent.config.interceptor.IdemPotentInterceptor;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author Rot
 * @date 2020/4/26 17:13
 */
@Configuration
@AllArgsConstructor
public class WebConfiguration extends WebMvcConfigurerAdapter {

    private final IdemPotentInterceptor idemPotentInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(idemPotentInterceptor);
        super.addInterceptors(registry);
    }
}
