package com.portfolio1.framework.session;

import org.springframework.data.redis.core.HashOperations;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class SessionInterceptor extends HandlerInterceptorAdapter {
    @Resource(name = "redisTemplate")
    private HashOperations<String, String, String> hashOperations;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        /*
        todo:
        react에서 권한 체크가 불가하거나 추가 권한 체크가 필요할시 여기에서 체크
        */
        return true;
    }
}
