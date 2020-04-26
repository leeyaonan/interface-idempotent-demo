package com.leeyaonan.idempotent.config.interceptor;

import com.leeyaonan.idempotent.annotations.InterceptorIdempotent;
import com.leeyaonan.idempotent.service.TokenService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;

/**
 * @author Rot
 * @date 2020/4/26 17:14
 */
@Component
@AllArgsConstructor
@Slf4j
public class IdemPotentInterceptor implements HandlerInterceptor {

    private final TokenService tokenService;

    /**
     * 预处理
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        if (!(handler instanceof HandlerMethod)) {
            return true;
        }

        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        // 被ApiIdempotment标记的扫描
        InterceptorIdempotent methodAnnotation = method.getAnnotation(InterceptorIdempotent.class);
        if (null != methodAnnotation) {
            try {
                return tokenService.checkToken(request);
            } catch (Exception e) {
                wirteReturnJson(response, e.getMessage());
                throw e;
            }
        }

        // 必须返回true，否则会被拦截一切请求
        return true;
    }

    /**
     * 返回的json值
     * @param response
     * @param json
     */
    private void wirteReturnJson(HttpServletResponse response, String json) {
        PrintWriter writer = null;
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");
        try {
            writer = response.getWriter();
            writer.print(json);
        } catch (IOException e) {

        } finally {
            if (null != writer) {
                writer.close();
            }
        }
    }
}
