package com.ssafy.home.Heo.ai.service;

import com.ssafy.home.Heo.ai.dto.in.HouseInfoRequestDto;
import com.ssafy.home.Heo.commercial.dto.out.CommercialResponseStatDto;

public interface HouseSummaryService {
    String generateHouseSummaryHtml(String aptSeq, HouseInfoRequestDto houseInfoRequestDto,
                                    String commercialInfo);

}
