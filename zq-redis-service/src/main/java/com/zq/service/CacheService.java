package com.zq.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
@Service
public class CacheService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CacheService.class);


    @Autowired
    private JedisPool jedisPool;

    /**
     * 存储一个键对值
     * @param key
     * @param value
     * @return
     */

    public void set(String key, String value){
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            jedis.set(key, value);
            LOGGER.info("缓存设置成功"+key +"=>" +value);
        }catch (Exception e){
            e.printStackTrace();
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
            String value = jedis.get(key);
            LOGGER.info("缓存获取成功"+key +"=>" +value);
            return value;
        } catch (Exception e){
            e.printStackTrace();
            return "";
        }
    }

    /**
     * 设置缓存过期时间
     * @param key
     * @param seconds
     * @param value
     * @return
     */
    public void setex(String key, int seconds,String value){
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            jedis.setex(key,seconds,value);
            LOGGER.info("缓存设置成功"+key +"=>" +value);
        }catch (Exception e){
            e.printStackTrace();
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
        }
        return i;
    }

}

