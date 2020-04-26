package com.leeyaonan.idempotent.service.impl;

import com.leeyaonan.idempotent.model.constant.RedisConstant;
import com.leeyaonan.idempotent.service.TokenService;
import com.leeyaonan.idempotent.utils.RedisUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

/**
 * @author Rot
 * @date 2020/4/26 17:05
 */
@Service
@AllArgsConstructor
public class TokenServiceImpl implements TokenService {

    private final RedisUtils redisUtils;

    /** 默认失效时间 */
    private static final Long DEFAULT_EXPIRE_TIME = 1000L;

    @Override
    public String createToken(Long expireTime) {

        if (null == expireTime) {
            expireTime = DEFAULT_EXPIRE_TIME;
        }

        String uuid = UUID.randomUUID().toString();
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(RedisConstant.TOKEN_PREFIX).append(uuid);
        String token = stringBuffer.toString();
        redisUtils.setEx(token, token, expireTime);
        if (!StringUtils.isEmpty(token)) {
            return token;
        }

        return null;
    }

    @Override
    public boolean checkToken(HttpServletRequest request) throws Exception {

        String token = request.getHeader(RedisConstant.TOKEN_NAME);

        // 如果header中不存在token，则从parameter中找
        if (StringUtils.isEmpty(token)) {
            token = request.getParameter(RedisConstant.TOKEN_NAME);
            if (StringUtils.isEmpty(token)) {
                throw new Exception("请求缺少Token");
            }
        }

        if (!redisUtils.exists(token)) {
            throw new Exception("重复操作");
        }

        boolean remove = redisUtils.remove(token);

        if (!remove) {
            throw new Exception("删除Token失败");
        }

        return true;
    }
}
