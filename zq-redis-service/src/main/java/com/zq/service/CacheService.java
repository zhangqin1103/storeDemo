package com.zq.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
@Service
public class CacheService {

    @Autowired
    private JedisPool jedisPool;

    /**
     * 存储一个键对值
     * @param key
     * @param value
     * @return
     */

    public String set(String key, String value){
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.set(key, value);
        } finally {
            jedisPool.returnResource(jedis);
        }
    }

    /**
     * 根据key 获取value
     * @param key
     * @return
     */
    public String get(String key){
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.get(key);
        } finally {
            jedisPool.returnResource(jedis);
        }
    }

    /**
     * 设置缓存过期时间
     * @param key
     * @param seconds
     * @param value
     * @return
     */
    public String setex(String key, int seconds,String value){
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.setex(key,seconds,value);
        } finally {
            jedisPool.returnResource(jedis);
        }
    }



    /**
     * 将 key 中储存的数字加上指定的增量值，
     * 如果 key 不存在，那么 key 的值会先被初始化为 0 ，
     * 然后再执行 INCR 操作。
     * @param key
     * @param increament
     * @return
     */

    public Long incrBy(String key, long increament) {
        Long i = null;
        Jedis jedis = null;
        try {
            jedis =jedisPool.getResource();
            i = jedis.incrBy(key, increament);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            jedisPool.returnResource(jedis);
        }
        return i;
    }

}

