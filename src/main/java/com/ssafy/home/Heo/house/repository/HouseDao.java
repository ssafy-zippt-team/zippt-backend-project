package com.ssafy.home.Heo.house.repository;

import com.ssafy.home.Heo.common.page.PageRequestDto;
import com.ssafy.home.Heo.common.page.PageResponseDto;
import com.ssafy.home.Heo.house.dto.out.HouseDetailResponseDto;
import com.ssafy.home.Heo.house.dto.out.HouseResponseDto;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;

@Mapper
@Repository
public interface HouseDao {
    HouseDetailResponseDto findHouseByAptSeq(String aptSeq) throws SQLException;

    int getHouseListCount() throws SQLException;
    List<HouseResponseDto> getHouseList(PageRequestDto pageRequestDto) throws SQLException;
}
