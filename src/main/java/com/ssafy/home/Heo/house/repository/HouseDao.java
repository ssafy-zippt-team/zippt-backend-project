package com.ssafy.home.Heo.house.repository;

import com.ssafy.home.Heo.common.page.PageRequestDto;
import com.ssafy.home.Heo.common.page.PageResponseDto;
import com.ssafy.home.Heo.house.dto.out.HouseDetailResponseDto;
import com.ssafy.home.Heo.house.dto.out.HouseResponseDto;
import com.ssafy.home.Heo.house.entity.HouseEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;

@Mapper
@Repository
public interface HouseDao {
    HouseEntity findHouseByAptSeq(String aptSeq) throws SQLException;

    int getHouseListCount() throws SQLException;
    List<HouseEntity> getHouseList(PageRequestDto pageRequestDto) throws SQLException;
}
