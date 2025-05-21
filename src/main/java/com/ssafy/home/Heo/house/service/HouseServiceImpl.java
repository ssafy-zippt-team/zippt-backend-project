package com.ssafy.home.Heo.house.service;

import com.ssafy.home.Heo.common.base.BaseResponseStatus;
import com.ssafy.home.Heo.common.exception.BaseException;
import com.ssafy.home.Heo.common.page.PageRequestDto;
import com.ssafy.home.Heo.common.page.PageResponseDto;
import com.ssafy.home.Heo.house.condition.SearchCondition;
import com.ssafy.home.Heo.house.dto.out.*;
import com.ssafy.home.Heo.house.entity.HouseEntity;
import com.ssafy.home.Heo.house.repository.HouseDao;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class HouseServiceImpl implements HouseService {
    private final HouseDao dao;
    private final RedisHouseService redisHouseService;

    @Override
    public HouseDetailResponseDto findHouseByAptSeq(String aptSeq) throws SQLException {
        HouseEntity entity = dao.findHouseByAptSeq(aptSeq);
        if(entity == null) throw new BaseException(BaseResponseStatus.NO_EXIST_HOUSE);
        return HouseDetailResponseDto.from(entity);
    }

    @Override
    public PageResponseDto<HouseResponseDto> getHouseList(PageRequestDto pageRequestDto) throws SQLException {
        // 1. 목록 조회
        List<HouseEntity> list = dao.getHouseList(pageRequestDto);
        if(list.isEmpty()) throw new BaseException(BaseResponseStatus.NO_EXIST_HOUSE); // 결과 없으면 에러 메시지
        // 2. 전체 개수 조회
        int totalCount = dao.getHouseListCount();
        // 3. 응답 조립
        return PageResponseDto.<HouseResponseDto> withAll()
                .dtoList(list.stream()
                        .map(HouseResponseDto::from)
                        .collect(Collectors.toList()))
                .totalCount(totalCount)
                .pageRequestDTO(pageRequestDto)
                .build();
    }

    @Override
    public PageResponseDto<HouseResponseDto> findHousesByCondition(SearchCondition searchCondition) throws SQLException {
        List <HouseEntity> list = dao.findHousesByCondition(searchCondition);
        int totalCount = dao.getHouseCountByCondition(searchCondition);
        return PageResponseDto.<HouseResponseDto> withAll()
                .dtoList(list.stream().map(HouseResponseDto::from).collect(Collectors.toList()))
                .totalCount(totalCount)
                .pageRequestDTO(searchCondition)
                .build();
    }



    @Override
    public List<HouseMarkerResponseDto> findHousesByLatLngRange(double minLat, double maxLat, double minLng, double maxLng) throws SQLException {
        List<HouseMarkerResponseDto> list = dao.findHousesByLatLngRange(minLat, maxLat, minLng, maxLng);
        List<String> aptSeqList = list.stream()
                .map(HouseMarkerResponseDto::getAptSeq)
                .toList();
        // 위도경도로 검색결과가 있으면
        if (!aptSeqList.isEmpty()) {
            // 검색결과의 id 리스트로 평균,최고,최저가 검색
            List<HouseDealAmountInfoResponseDto> dealList = dao.findAllHouseDealAvgByAptSeqList(aptSeqList);
            // 데이터 조립
            list.forEach(dto -> {
                HouseDealAmountInfoResponseDto deal = dealList.stream()
                        .filter(d -> d.getAptSeq().equals(dto.getAptSeq()))
                        .findFirst()
                        .orElse(null);
                if (deal != null) {
                    dto.setAmountAvg(deal.getAmountAvg());
                    dto.setAmountMax(deal.getAmountMax());
                    dto.setAmountMin(deal.getAmountMin());
                }
            });
        }
        return list;
    }

}

