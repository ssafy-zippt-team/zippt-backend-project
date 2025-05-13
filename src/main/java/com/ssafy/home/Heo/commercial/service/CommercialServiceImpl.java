package com.ssafy.home.Heo.commercial.service;

import com.ssafy.home.Heo.bookmark.dto.in.BookmarkSaveDto;
import com.ssafy.home.Heo.bookmark.dto.out.BookmarkResponseDto;
import com.ssafy.home.Heo.bookmark.repository.BookmarkDao;
import com.ssafy.home.Heo.bookmark.service.BookmarkService;
import com.ssafy.home.Heo.commercial.dto.in.CommercialRequestRadiusDto;
import com.ssafy.home.Heo.commercial.vo.in.CommercialRequestRadiusVo;
import com.ssafy.home.Heo.config.CommercialApiConfig;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.sql.SQLException;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CommercialServiceImpl implements CommercialService {

    private final CommercialApiConfig commercialApiConfig;

    public List<CommercialRequestRadiusVo> getCommercialInRadius(CommercialRequestRadiusDto dto) {
        String url = UriComponentsBuilder
                .fromHttpUrl(commercialApiConfig.getBaseUrl() + "/storeZoneInRadius")
                .queryParam("serviceKey", commercialApiConfig.getServiceKey())
                .queryParam("cx", dto.getCx())
                .queryParam("cy", dto.getCy())
                .queryParam("radius", dto.getRadius())
                .queryParam("type", "json")
                .queryParam("numOfRows", 100)
                .queryParam("pageNo", 1)
                .toUriString();

        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(url, String.class);
    }
}
