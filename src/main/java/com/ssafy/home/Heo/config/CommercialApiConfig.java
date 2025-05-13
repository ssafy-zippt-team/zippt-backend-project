package com.ssafy.home.Heo.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import lombok.Getter;
import lombok.Setter;

@Component
@ConfigurationProperties(prefix = "commercial-api")
@Getter @Setter
public class CommercialApiConfig {
        private String baseUrl;
        private String serviceKey;
}

