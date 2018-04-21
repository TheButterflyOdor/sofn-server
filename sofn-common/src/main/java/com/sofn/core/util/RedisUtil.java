package com.sofn.core.util;

import com.sofn.core.constant.OperateLog;
import com.sofn.core.constant.OperateLogComparator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;
import redis.clients.jedis.JedisCluster;

import java.io.Serializable;
import java.util.*;
import java.util.concurrent.TimeUnit;
import org.springframework.util.StringUtils;

/**
 * Redis缓存辅助类
 *
 * @author sofn
 * @version 2016年4月2日 下午4:17:22          使用 RedisTemplate          使用此工具类
 */
//@PropertySource("classpath:config/redis.properties")
public final class RedisUtil {
    /**
     * Instantiates a new Redis util.
     */
    private RedisUtil() {
    }

    /**
     * The Logger.
     */
    private static Logger logger = LoggerFactory.getLogger(RedisUtil.class);
    /**
     * The Expire.
     */
    private static Integer EXPIRE = PropertiesUtil.getInt("redis.expiration");


    /**
     * Gets redis.
     * 获取连接 单例 双锁 (效率不高)
     *
     * @return the redis
     */
    @SuppressWarnings("unchecked")
    private static RedisTemplate<Serializable, Serializable> getRedis() {
        //if (redisTemplate == null) {
        //    synchronized (RedisUtil.class) {
        //        if (redisTemplate == null) {
        //            WebApplicationContext wac = ContextLoader.getCurrentWebApplicationContext();
        //            redisTemplate = (RedisTemplate<Serializable, Serializable>)wac.getBean("redisTemplate");
        //        }
        //    }
        //}
        WebApplicationContext wac = ContextLoader.getCurrentWebApplicationContext();
        return (RedisTemplate<Serializable, Serializable>) wac.getBean("redisTemplate");
    }

    private static JedisCluster getJedisCluster() {
        WebApplicationContext wac = ContextLoader.getCurrentWebApplicationContext();
        return (JedisCluster) wac.getBean("jedisCluster");
    }

    /**
     * Get serializable.
     *
     * @param key the key
     * @return the serializable
     */
    public static Serializable get(final String key) {
        expire(key, EXPIRE);
        logger.debug("[get key] key = {} ", key);
        return getRedis().opsForValue().get(key);
    }

    /**
     * Get serializable.
     *
     * @param key the key
     * @return the serializable
     */
    public static Serializable get(final String key, final int expire) {
        if (expire > -1)
            expire(key, expire);
        logger.debug("[get key] key = {} ", key);
        return getRedis().opsForValue().get(key);
    }

    /**
     * Set.
     *
     * @param key   the key
     * @param value the value
     */
    public static void set(final String key, final Serializable value) {
        logger.debug(value.toString());
        getRedis().boundValueOps(key).set(value);
        logger.debug("[set key] key = {} , value = {}", key, value);
        expire(key, EXPIRE);
    }
//    public static void set(final String key, final Serializable value) {
//        logger.debug(value.toString());
//        getJedisCluster().setex(SerializingUtil.serialize(key), EXPIRE, SerializingUtil.serialize(value));
//        logger.debug("[set key] key = {} , value = {}", key, value);
//    }

    /**
     * Set.永久
     *
     * @param key   the key
     * @param value the value
     */
    public static void setForever(final String key, final Serializable value) {
        logger.debug(value.toString());
        getRedis().boundValueOps(key).set(value);
        logger.debug("[set key] key = {} , value = {}", key, value);
    }
//    public static void setForever(final String key, final Serializable value) {
//
//        logger.debug(value.toString());
//        getJedisCluster().setnx(SerializingUtil.serialize(key), SerializingUtil.serialize(value));
//        logger.debug("[set key] key = {} , value = {}", key, value);
//    }

    /**
     * Set.
     *
     * @param key    the key
     * @param value  the value
     * @param expire the seconds
     */
//    public static void set(final String key, final Serializable value, final int seconds) {
//        getRedis().boundValueOps(key).set(value);
//        logger.debug("[set key] key = {} , value = {}, time = {}", key, value, seconds);
//        expire(key, seconds);
//    }
    public static void set(final String key, final Serializable value, final int expire) {
        logger.debug(value.toString());
        getRedis().boundValueOps(key).set(value);
        if (expire > -1)
            expire(key, expire);
//        getJedisCluster().setex(SerializingUtil.serialize(key), seconds, SerializingUtil.serialize(value));
        logger.debug("[set key] key = {} , value = {}", key, value);
    }

    public static void setNotTimeOut(final String key, final Serializable value) {
        getRedis().boundValueOps(key).set(value);
        logger.debug("[set key] key = {} , value = {}", key, value);
        expire(key, 999999999);
    }

    /**
     * Exists boolean.
     *
     * @param key the key
     * @return the boolean
     */
    public static Boolean exists(final String key) {
        logger.debug("[exists key] key = {} ", key);
        expire(key, EXPIRE);
        return getRedis().hasKey(key);
    }

    public static Boolean exists(final String key, final int expire) {
        logger.debug("[exists key] key = {} ", key);
        if (expire > -1)
            expire(key, expire);
        return getRedis().hasKey(key);
    }

    /**
     * Del.
     *
     * @param key the key
     */
    public static void del(final String key) {
        logger.debug("[del key] key = {} ", key);
        getRedis().delete(key);
    }

    /**
     * Del all.
     *
     * @param pattern the pattern
     */
    public static void delAll(final String pattern) {
        getRedis().delete(getRedis().keys(pattern));
    }

    /**
     * Type string.
     *
     * @param key the key
     * @return the string
     */
    public static String type(final String key) {
        expire(key, EXPIRE);
        logger.debug("[key key] key = {} ", key);
        return getRedis().type(key).getClass().getName();
    }

    /**
     * 在某段时间后失效
     *
     * @param key     the key
     * @param seconds the seconds
     * @return boolean
     */
    public static Boolean expire(final String key, final int seconds) {
        return getRedis().expire(key, seconds, TimeUnit.SECONDS);
    }

    /**
     * 在某个时间点失效
     *
     * @param key      the key
     * @param unixTime the unix time
     * @return boolean
     */
    public static Boolean expireAt(final String key, final long unixTime) {
        return getRedis().expireAt(key, new Date(unixTime));
    }

    /**
     * Ttl long.
     *
     * @param key the key
     * @return the long
     */
    public static Long ttl(final String key) {
        return getRedis().getExpire(key, TimeUnit.SECONDS);
    }

    /**
     * Sets .
     *
     * @param key    the key
     * @param offset the offset
     * @param value  the value
     */
    public static void setrange(final String key, final long offset, final String value) {
        expire(key, EXPIRE);
        getRedis().boundValueOps(key).set(value, offset);
    }

    /**
     * Gets .
     *
     * @param key         the key
     * @param startOffset the start offset
     * @param endOffset   the end offset
     * @return the
     */
    public static String getrange(final String key, final long startOffset, final long endOffset) {
        expire(key, EXPIRE);
        return getRedis().boundValueOps(key).get(startOffset, endOffset);
    }

    /**
     * Gets set.
     *
     * @param key   the key
     * @param value the value
     * @return the set
     */
    public static Serializable getSet(final String key, final String value) {
        expire(key, EXPIRE);
        return getRedis().boundValueOps(key).getAndSet(value);
    }

    /**
     * 递增
     *
     * @param redisKey the redis key
     * @return the long
     */
    public static Long incr(final String redisKey) {
        return getRedis().execute(new RedisCallback<Long>() {
            public Long doInRedis(RedisConnection connection) throws DataAccessException {
                return connection.incr(redisKey.getBytes());
            }
        });
    }
    // 未完，待续...

    public static List<OperateLog> keys() {
        Set<Serializable> result = getRedis().keys("operateLog*");
        List<OperateLog> operateLogs = new ArrayList<>();
        for (Serializable serializable : result) {
            OperateLog operateLog = (OperateLog) get(serializable.toString());
            if (operateLog != null) {
                operateLogs.add(operateLog);
            }
        }

        OperateLogComparator oComparator = new OperateLogComparator();
        Collections.sort(operateLogs,oComparator);
        return operateLogs;
    }

    /**
     * 按条件获取操作日志
     * @param operateUsername 操作用户名
     * @return 操作日志
     */
    public static List<OperateLog> getOperateLogsByCondition(String operateUsername) {
        Set<Serializable> result = getRedis().keys("operateLog*");
        List<OperateLog> operateLogs = new ArrayList<>();

        for (Serializable serializable : result) {
            OperateLog operateLog = (OperateLog) get(serializable.toString());
            if (operateLog != null) {
                if (StringUtils.isEmpty(operateUsername)) {
                    operateLogs.add(operateLog);
                } else {
                    if (operateUsername.equals(operateLog.getOperateUsername())) {
                        operateLogs.add(operateLog);
                    }
                }
            }
        }

        Collections.sort(operateLogs, new OperateLogComparator());
        return operateLogs;
    }
}
