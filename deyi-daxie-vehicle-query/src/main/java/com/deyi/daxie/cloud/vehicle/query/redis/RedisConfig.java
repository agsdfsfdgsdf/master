package com.deyi.daxie.cloud.vehicle.query.redis;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * Description:
 * @date 2022/9/22
 * @author Huang ShuYing
 */
//@Configuration
public class RedisConfig {

    /**
     * Description: 注入 Spring 容器中，忽略所有警告
     * @date 2022/9/22
     * @author Huang ShuYing
     */
    @Bean
    public <K, T> RedisTemplate<K, T> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        //配置redisTemplate
        RedisTemplate<K, T> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        // 创建Jackson2JsonRedisSerialize序列化器
        Jackson2JsonRedisSerializer<?> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<>(Object.class);
        // 创建stringRedisSerializer序列化器
        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
        //序列化类，对象映射设置
        redisTemplate.setKeySerializer(stringRedisSerializer);
        redisTemplate.setValueSerializer(jackson2JsonRedisSerializer);
        redisTemplate.setHashKeySerializer(stringRedisSerializer);
        redisTemplate.setHashValueSerializer(jackson2JsonRedisSerializer);
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }

}
