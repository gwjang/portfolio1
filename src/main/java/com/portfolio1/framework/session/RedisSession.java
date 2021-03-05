package com.portfolio1.framework.session;

import org.springframework.data.redis.core.HashOperations;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

import javax.annotation.Resource;

@EnableRedisHttpSession
public class RedisSession {
}
