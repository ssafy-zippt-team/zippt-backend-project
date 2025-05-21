package com.ssafy.home.Heo.house.service;

import com.ssafy.home.Heo.common.page.PageRequestDto;
import com.ssafy.home.Heo.common.page.PageResponseDto;
import com.ssafy.home.Heo.house.condition.SearchCondition;
import com.ssafy.home.Heo.house.dto.out.HouseDetailResponseDto;
import com.ssafy.home.Heo.house.dto.out.HouseMarkerResponseDto;
import com.ssafy.home.Heo.house.dto.out.HouseResponseDto;

import java.sql.SQLException;
import java.util.List;

public interface HouseService{
    HouseDetailResponseDto findHouseByAptSeq(String aptSeq) throws SQLException;

    PageResponseDto<HouseResponseDto> getHouseList(PageRequestDto pageRequestDTO) throws SQLException;

    PageResponseDto<HouseResponseDto> findHousesByCondition(SearchCondition searchCondition) throws SQLException;


    List<HouseMarkerResponseDto> findHousesByLatLngRange(double minLat, double maxLat,
                                                         double minLng, double maxLng)  throws SQLException;

}
