package com.ssafy.home.Heo.vector;

import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.ai.vectorstore.redis.RedisVectorStore;
import org.springframework.ai.vectorstore.redis.RedisVectorStore.MetadataField;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.DefaultJedisClientConfig;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisPooled;

@Configuration
public class AiVectorStoreConfig {

    @Value("${spring.ai.vectorstore.redis.index}")
    private String index;

    @Value("${spring.ai.vectorstore.redis.prefix}")
    private String prefix;

    @Value("${spring.data.redis.host}")
    private String redisHost;

    @Value("${spring.data.redis.port}")
    private int redisPort;

    @Value("${spring.data.redis.password:}") // 비어있을 수도 있으므로 기본값은 빈 문자열
    private String redisPassword;

    @Bean
    public VectorStore vectorStore(EmbeddingModel embeddingModel) {
        JedisPooled jedis = redisPassword.isBlank()
                ? new JedisPooled(redisHost, 6380)
                : new JedisPooled(
                new HostAndPort(redisHost, 6380),
                DefaultJedisClientConfig.builder()
                        .password(redisPassword)
                        .build()
        );

        return RedisVectorStore.builder(jedis, embeddingModel)
                .indexName(index)
                .prefix(prefix)
                .metadataFields(
                        MetadataField.tag("apt_seq"),
                        MetadataField.text("full_name"),
                        MetadataField.text("img_url")
                )
                .initializeSchema(true)
                .build();
    }
}
