package com.qtatelier.config;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.io.Serializable;
import java.text.MessageFormat;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @description: redis工具
 * @author:JazzXia(kima_riiiiii)
 * @date:2019-12-13 15:31
 * @contact:jazzxiaw@qq.com & xia.weiwei163@163.com
 */
@Component("ToolRedisinfo")
public class ToolRedis /*extends com.qtatelier.dev_util.tool.ToolRedis*/ {
    private RedisTemplate redisTemplate;
    private static Logger logger = LoggerFactory.getLogger(ToolRedis.class);
    @Resource(
            name = "redisTemplate"
    )
    private ValueOperations valueOperations;
    @Resource(
            name = "redisTemplate"
    )
    private HashOperations hashOperations;
    @Resource(
            name = "redisTemplate"
    )
    private ListOperations listOperations;
    @Resource(
            name = "redisTemplate"
    )
    private SetOperations setOperations;
    @Resource(
            name = "redisTemplate"
    )
    private ZSetOperations zSetOperations;

    public ToolRedis() {
    }

    @Autowired
    private void setRedisTemplate(RedisTemplate redisTemplate) {
        Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(om);
        redisTemplate.setKeySerializer(jackson2JsonRedisSerializer);
        redisTemplate.setHashKeySerializer(jackson2JsonRedisSerializer);
        redisTemplate.setValueSerializer(jackson2JsonRedisSerializer);
        redisTemplate.setHashValueSerializer(jackson2JsonRedisSerializer);
        this.redisTemplate = redisTemplate;
    }

    public boolean expire(String key, Long expireTime) {
        return this.redisTemplate.expire(key, expireTime, TimeUnit.SECONDS);
    }

    public boolean updateExprieTime(final String key, Long expireTime) {
        return this.expire(key, expireTime);
    }

    public Long getAutoIncrement(String key) {
        return this.valueOperations.increment(key, 1L);
    }

    public Object get(final String key) {
        return this.valueOperations.get(key);
    }

    public boolean set(String key, Object value) {
        try {
            this.set(key, value, 2592000L);
            return true;
        } catch (Exception var4) {
            logger.error("redis塞值", var4);
            return false;
        }
    }

    public boolean set(String key, Object value, Long expireTime) {
        try {
            this.valueOperations.set(key, value, expireTime, TimeUnit.SECONDS);
            return true;
        } catch (Exception var5) {
            logger.error("redis塞值", var5);
            return false;
        }
    }

    public boolean hashPut(String key, String hashKey, Object hashValue, Long expireTime) {
        try {
            this.hashOperations.put(key, hashKey, hashValue);
            return this.expire(key, expireTime);
        } catch (Exception var6) {
            logger.error("redis塞值", var6);
            return false;
        }
    }

    public boolean hashPutAll(String key, Map<String, Object> hashKeyValue, Long expireTime) {
        try {
            this.hashOperations.putAll(key, hashKeyValue);
            return this.expire(key, expireTime);
        } catch (Exception var5) {
            logger.error("redis塞值", var5);
            return false;
        }
    }

    public Set zsetAllAsc(String key) {
        return this.zSetOperations.range(key, 0L, -1L);
    }

    public Set zsetAllDesc(String key) {
        return this.zSetOperations.reverseRange(key, 0L, -1L);
    }

    public boolean delKey(String key) {
        this.redisTemplate.delete(key);
        return true;
    }

    public boolean delKeys(Collection keys) {
        this.redisTemplate.delete(keys);
        return true;
    }

    public void removePattern(final String pattern) {
        Set<Serializable> keys = this.redisTemplate.keys(pattern);
        if (keys.size() > 0) {
            this.redisTemplate.delete(keys);
        }

    }

    public void remove(final String... keys) {
        String[] var2 = keys;
        int var3 = keys.length;

        for(int var4 = 0; var4 < var3; ++var4) {
            String key = var2[var4];
            this.remove(key);
        }

    }

    public void remove(final String key) {
        if (this.exists(key)) {
            this.redisTemplate.delete(key);
        }

    }

    public boolean existsKey(String key) {
        return this.redisTemplate.hasKey(key);
    }

    public boolean exists(final String key) {
        return this.redisTemplate.hasKey(key);
    }

    public boolean renamenx(String oldKey, String newKey) {
        try {
            return this.redisTemplate.renameIfAbsent(oldKey, newKey);
        } catch (InvalidDataAccessApiUsageException var4) {
            logger.error("redis塞值", var4);
            return false;
        }
    }

    public boolean rename(String oldKey, String newKey) {
        try {
            this.redisTemplate.rename(oldKey, newKey);
            return true;
        } catch (InvalidDataAccessApiUsageException var4) {
            logger.error("redis塞值", var4);
            return false;
        }
    }

    public static String generatorKey(String regex, Object... values) {
        return StringUtils.isEmpty(regex) ? "" : MessageFormat.format(regex, values);
    }

    public boolean getLock(String key, long maxCount, long timeout) {
        try {
            long count = this.redisTemplate.opsForValue().increment(key, 1L);
            if (count == 1L) {
                this.redisTemplate.expire(key, timeout, TimeUnit.SECONDS);
            }

            return maxCount >= count;
        } catch (Exception var8) {
            logger.error("redis加锁异常", var8);
            this.redisTemplate.delete(key);
            return false;
        }
    }

    public boolean setJson(String key, Object value) {
        try {
            this.set(key, JSONObject.toJSON(value));
            return true;
        } catch (Exception var4) {
            logger.error("redis塞值", var4);
            return false;
        }
    }

    public JSONObject getJson(String key) {
        try {
            Object str = this.valueOperations.get(key);
            if (null != str) {
                return JSONObject.parseObject((String)str);
            }
        } catch (Exception var3) {
            logger.error("redis塞值", var3);
        }

        return null;
    }
}
