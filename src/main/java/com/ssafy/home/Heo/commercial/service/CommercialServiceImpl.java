package com.ssafy.home.Heo.commercial.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ssafy.home.Heo.commercial.dto.in.CommercialRequestRadiusDto;
import com.ssafy.home.Heo.commercial.dto.out.CommercialResponseRadiusDto;
import com.ssafy.home.Heo.commercial.entity.CommercialEntity;
import com.ssafy.home.Heo.config.CommercialApiConfig;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CommercialServiceImpl implements CommercialService {

    private final CommercialApiConfig commercialApiConfig;


    @Override
    public List<CommercialResponseRadiusDto> getCommercialInRadius(CommercialRequestRadiusDto dto) throws UnsupportedEncodingException, URISyntaxException, JsonProcessingException {
        System.out.println("dto = " + dto);

        RestTemplate restTemplate = new RestTemplate();

        URI uri = new URI(commercialApiConfig.getBaseUrl() + "/storeZoneInRadius" +
                "?serviceKey=" + URLEncoder.encode(commercialApiConfig.getServiceKey(),StandardCharsets.UTF_8) +
                "&cx=" + dto.getCx() +
                "&cy=" + dto.getCy() +
                "&radius=" + dto.getRadius() +
                "&type=json" +
                "&numOfRows=100" +
                "&pageNo=1");

        // 요청 URL 확인
        System.out.println("✅ 요청 URL: " + uri.toString());
        log.info("✅ 인코딩한 서비스키: {}", URLEncoder.encode(commercialApiConfig.getServiceKey(),StandardCharsets.UTF_8));
        log.info("✅ 서비스키 : {}", commercialApiConfig.getServiceKey());
        log.info("✅ baseUrl: {}", commercialApiConfig.getBaseUrl());

        // API 호출
        CommercialEntity response =
                restTemplate.getForObject(uri, CommercialEntity.class);

        log.info("✅ 응답 Entity: {}", response);
        if (response != null && response.getBody() != null && response.getBody().getItems() != null) {
            return response.getBody().getItems().stream()
                    .map(CommercialEntity.Body.CommercialArea::from)
                    .toList();
        }
        return null;
    }
}
