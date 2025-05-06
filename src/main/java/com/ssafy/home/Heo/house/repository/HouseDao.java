package com.ssafy.home.Heo.house.repository;

import com.ssafy.home.Heo.house.dto.out.HouseDetailResponseDto;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;

@Mapper
@Repository
public interface HouseDao {
    HouseDetailResponseDto findHouseByAptSeq(String aptSeq) throws SQLException;
}
