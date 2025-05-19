package com.ssafy.home.Heo.commercial.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ssafy.home.Heo.commercial.dto.in.CommercialRequestRadiusDto;
import com.ssafy.home.Heo.commercial.dto.in.CommercialRequestStatDto;
import com.ssafy.home.Heo.commercial.dto.out.CommercialResponseRadiusDto;
import com.ssafy.home.Heo.commercial.dto.out.CommercialResponseStatDto;
import com.ssafy.home.Heo.commercial.dto.out.CommercialResponseStoreDto;

import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.util.List;

public interface CommercialService {

    public List<CommercialResponseRadiusDto> getCommercialInRadius(CommercialRequestRadiusDto dto) throws UnsupportedEncodingException, URISyntaxException, JsonProcessingException;

    public List<CommercialResponseStoreDto> getStoreInCommercial(String trarNo) throws UnsupportedEncodingException, URISyntaxException, JsonProcessingException;

    public CommercialResponseStatDto getCategoryStatistics(CommercialRequestStatDto dto) throws Exception;
}
