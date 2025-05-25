package com.ssafy.home.Heo.config;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.ssafy.home.Heo.cache.dto.out.RecentViewHouseResponseDto;
import com.ssafy.home.Heo.house.dto.out.LookAroundCacheDto;
import jakarta.annotation.PostConstruct;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.util.Date;

import java.util.Arrays;
@Configuration
@EnableRedisRepositories
@Log4j2
public class RedisConfig  {

    @Value("${spring.data.redis.host}")
    private String host;

    @Value("${spring.data.redis.port}")
    private int port;

    @Value("${spring.data.redis.password}")
    private String password;

    @Bean
    public RedisConnectionFactory redisConnectionFactory() {
        RedisStandaloneConfiguration redisConfiguration = new RedisStandaloneConfiguration();
        redisConfiguration.setHostName(host);
        redisConfiguration.setPort(port);
        redisConfiguration.setPassword(password);

        log.info("RedisConnectionFactory 생성됨 → host: {}, port: {}", host, port);

        LettuceConnectionFactory factory = new LettuceConnectionFactory(redisConfiguration);
        factory.afterPropertiesSet(); // 연결 확인

        try {
            factory.getConnection().ping();  // 연결 테스트
            log.info("Redis 연결 성공");
        } catch (Exception e) {
            log.error("Redis 연결 실패: {}", e.getMessage());
        }

        return factory;
    }

    @Bean
    @Primary
    public RedisTemplate<String, String> redisTemplate() {
        RedisTemplate<String, String> redisTemplate = new RedisTemplate<>();
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new StringRedisSerializer());
        redisTemplate.setConnectionFactory(redisConnectionFactory());
        return redisTemplate;
    }

    @Bean
    public RedisTemplate<String, LookAroundCacheDto> lookAroundRedisTemplate() {
        RedisTemplate<String, LookAroundCacheDto> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory());

        // Key Serializer 설정
        template.setKeySerializer(new StringRedisSerializer());

        // Value Serializer 설정
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule()); // LocalDateTime 등 대응

        Jackson2JsonRedisSerializer<LookAroundCacheDto> serializer =
                new Jackson2JsonRedisSerializer<>(LookAroundCacheDto.class);
        template.setValueSerializer(serializer);

        template.afterPropertiesSet();
        return template;
    }

    @Bean
    public RedisTemplate<String, RecentViewHouseResponseDto> recentViewHouseRedisTemplate() {
        RedisTemplate<String, RecentViewHouseResponseDto> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory());

        // Key Serializer 설정
        template.setKeySerializer(new StringRedisSerializer());

        // Value Serializer 설정
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule()); // LocalDateTime 등 대응

        Jackson2JsonRedisSerializer<RecentViewHouseResponseDto> serializer =
                new Jackson2JsonRedisSerializer<>(RecentViewHouseResponseDto.class);
        template.setValueSerializer(serializer);

        template.afterPropertiesSet();
        return template;
    }

}