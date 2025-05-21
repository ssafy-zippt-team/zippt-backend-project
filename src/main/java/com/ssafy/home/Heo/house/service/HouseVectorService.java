package com.ssafy.home.Heo.house.service;


import com.ssafy.home.Heo.house.dto.out.HouseSimilarResponseDto;

import java.util.List;

public interface HouseVectorService
{
    // 아파트 이미지와 유사한 아파트 조회 4개 리턴
    List<HouseSimilarResponseDto> getHouseTermSimilar(String term);

    // 이름으로 유사한 아파트명 4개 리턴
    List<HouseSimilarResponseDto> getHouseImageSimilar(String aptSeq) throws Exception;
}
