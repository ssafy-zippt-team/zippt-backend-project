package com.ssafy.home.Heo.house.repository;

import com.ssafy.home.Heo.common.page.PageRequestDto;

import com.ssafy.home.Heo.house.condition.SearchCondition;
import com.ssafy.home.Heo.house.dto.out.BookmarkCountDto;
import com.ssafy.home.Heo.house.dto.out.HouseDealAmountInfoResponseDto;
import com.ssafy.home.Heo.house.dto.out.HouseMarkerResponseDto;
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

    /**
     * 프론트에 마커로 내보낼 메서드들
     */
    // 검색조건[시군구 코드+읍면동 코드]로 모든 집정보 가져옴
    List <HouseMarkerResponseDto> findAllHousesByDong(String sggCd, String umdCd)throws SQLException;
    // List<아파트시퀀스> 로 아파트들의 매매가 평균 조회
    List<HouseDealAmountInfoResponseDto> findAllHouseDealAvgByAptSeqList(List<String> aptSeqList) throws  SQLException;
    List<BookmarkCountDto> findAllBookmarkCountByAptSeqList(List<String> aptSeqList) throws  SQLException;

    List<HouseMarkerResponseDto> findHousesByLatLngRange(double minLat,
                                                         double maxLat,
                                                         double minLng,
                                                         double maxLng) throws SQLException;
    //;

}
