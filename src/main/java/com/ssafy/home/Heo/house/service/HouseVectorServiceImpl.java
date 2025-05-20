package com.ssafy.home.Heo.house.service;

import com.ssafy.home.Heo.house.dto.out.HouseSimilarResponseDto;
import com.ssafy.home.Heo.house.repository.HouseVectorDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class HouseVectorServiceImpl implements  HouseVectorService{
    private final HouseVectorDao dao;
    @Override
    public List<HouseSimilarResponseDto> getHouseTermSimilar(String term) {
        return dao.getHouseTermSimilar(term);
    }

    @Override
    public List<HouseSimilarResponseDto> getHouseImageSimilar(String aptSeq) throws Exception {
        return dao.getHouseImageSimilar(aptSeq);
    }

}
