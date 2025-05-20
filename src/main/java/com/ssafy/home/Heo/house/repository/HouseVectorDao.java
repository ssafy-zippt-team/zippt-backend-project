package com.ssafy.home.Heo.house.repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ssafy.home.Heo.house.dto.out.HouseSimilarResponseDto;

import java.util.List;

public interface HouseVectorDao {
    List<HouseSimilarResponseDto> getHouseTermSimilar(String term);

    List<HouseSimilarResponseDto> getHouseImageSimilar(String aptSeq) throws Exception;
}
