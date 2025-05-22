package com.ssafy.home.Heo.config;

import com.ssafy.home.Heo.ai.advisor.ReReadingAdvisor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;

@Configuration
public class AiConfig {
    @Value("${spring.ai.system-prompt}")
    String systemPrompt;

    @Bean
    ChatClient simpleChatClient(ChatClient.Builder builder) {
        return builder.defaultSystem(systemPrompt)
                .defaultAdvisors(new SimpleLoggerAdvisor(Ordered.LOWEST_PRECEDENCE - 1))
                .build();
    }

    /**
     * SimpleLoggerAdvisor와 ReReadingAdvisor를 적용한 ChatClient를 생성
     */
    @Bean
    ChatClient reReadingChatClient(ChatClient.Builder builder) {
        return builder.defaultSystem(systemPrompt)
                .defaultAdvisors(new SimpleLoggerAdvisor(Ordered.LOWEST_PRECEDENCE - 1), new ReReadingAdvisor(0))
                .build();
    }
}
