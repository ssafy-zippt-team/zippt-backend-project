package com.ssafy.home.Heo.house.service;

import com.ssafy.home.Heo.common.page.PageRequestDto;
import com.ssafy.home.Heo.common.page.PageResponseDto;
import com.ssafy.home.Heo.house.dto.out.HouseDetailResponseDto;
import com.ssafy.home.Heo.house.dto.out.HouseResponseDto;

import java.sql.SQLException;

public interface HouseService{
    HouseDetailResponseDto findHouseByAptSeq(String aptSeq) throws SQLException;

    PageResponseDto<HouseResponseDto> getHouseList(PageRequestDto pageRequestDTO) throws SQLException;
}
