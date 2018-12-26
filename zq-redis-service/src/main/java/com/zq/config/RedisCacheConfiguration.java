package com.zq.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * jedis连接池 配置类
 */
@Configuration
@EnableCaching
public class RedisCacheConfiguration extends CachingConfigurerSupport {

    Logger logger = LoggerFactory.getLogger(RedisCacheConfiguration.class);

    @Value("${spring.redis.host}")
    private String host;

    @Value("${spring.redis.port}")
    private int port;

    @Value("${spring.redis.timeout}")
    private String timeout;

    @Value("${spring.redis.jedis.pool.max-idle}")
    private int maxIdle;

    @Value("${spring.redis.jedis.pool.max-wait}")
    private String maxWaitMillis;


    @Bean
    public JedisPool redisPoolFactory() {

        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        
        jedisPoolConfig.setMaxIdle(maxIdle);

        jedisPoolConfig.setMaxWaitMillis(Long.valueOf(maxWaitMillis.substring(0,maxWaitMillis.length()-1)));

        JedisPool jedisPool = new JedisPool(jedisPoolConfig, host, port, Integer.valueOf(timeout.substring(0,timeout.length()-2)));

        return jedisPool;
    }
}