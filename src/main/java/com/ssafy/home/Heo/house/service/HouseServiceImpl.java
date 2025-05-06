package com.ssafy.home.Heo.house.service;

import com.ssafy.home.Heo.common.base.BaseResponseStatus;
import com.ssafy.home.Heo.common.exception.BaseException;
import com.ssafy.home.Heo.common.page.PageRequestDto;
import com.ssafy.home.Heo.common.page.PageResponseDto;
import com.ssafy.home.Heo.house.dto.out.HouseDetailResponseDto;
import com.ssafy.home.Heo.house.dto.out.HouseResponseDto;
import com.ssafy.home.Heo.house.repository.HouseDao;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class HouseServiceImpl implements HouseService {
    private final HouseDao dao;

    @Override
    public HouseDetailResponseDto findHouseByAptSeq(String aptSeq) throws SQLException {
        HouseDetailResponseDto res = dao.findHouseByAptSeq(aptSeq);
        if(res == null) throw new BaseException(BaseResponseStatus.NO_EXIST_HOUSE);
        return res;
    }

    @Override
    public PageResponseDto<HouseResponseDto> getHouseList(PageRequestDto pageRequestDto) throws SQLException {
        // 1. 목록 조회
        List<HouseResponseDto> list = dao.getHouseList(pageRequestDto);

        // 2. 전체 개수 조회
        int totalCount = dao.getHouseListCount();
        log.info("total = "+totalCount);
        // 3. 응답 조립
        return PageResponseDto.<HouseResponseDto> withAll()
                .dtoList(list)
                .totalCount(totalCount)
                .pageRequestDTO(pageRequestDto)
                .build();
    }
}
