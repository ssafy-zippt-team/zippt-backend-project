package com.ssafy.home.Heo.house.repository;

import com.ssafy.home.Heo.common.page.PageRequestDto;

import com.ssafy.home.Heo.house.condition.SearchCondition;
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


    // 검색조건[시군구 코드+읍면동 코드] + 정렬조건으로 페이지네이션
    List <HouseEntity> findHousesByCondition(SearchCondition searchCondition) throws SQLException;
    int getHouseCountByCondition(SearchCondition searchCondition) throws SQLException;

}
