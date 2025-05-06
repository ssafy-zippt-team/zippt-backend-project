package com.ssafy.home.Heo.house.service;

import com.ssafy.home.Heo.house.dto.out.HouseDetailResponseDto;
import lombok.RequiredArgsConstructor;

import java.sql.SQLException;

public interface HouseService{
    HouseDetailResponseDto findHouseByAptSeq(String aptSeq)throws SQLException;
}
