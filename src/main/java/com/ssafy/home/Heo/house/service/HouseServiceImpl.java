package com.ssafy.home.Heo.house.service;

import com.ssafy.home.Heo.common.base.BaseResponseStatus;
import com.ssafy.home.Heo.common.exception.BaseException;
import com.ssafy.home.Heo.common.page.PageRequestDto;
import com.ssafy.home.Heo.common.page.PageResponseDto;
import com.ssafy.home.Heo.deal.dto.out.DealInfoResponseDto;
import com.ssafy.home.Heo.house.condition.SearchCondition;
import com.ssafy.home.Heo.house.dto.out.HouseDetailResponseDto;
import com.ssafy.home.Heo.house.dto.out.HouseResponseDto;
import com.ssafy.home.Heo.house.entity.HouseEntity;
import com.ssafy.home.Heo.house.repository.HouseDao;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class HouseServiceImpl implements HouseService {
    private final HouseDao dao;

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
}
