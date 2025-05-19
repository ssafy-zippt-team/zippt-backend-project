package com.ssafy.home.Heo.deal.reposiitory;

import com.ssafy.home.Heo.deal.condition.OrderCondition;
import com.ssafy.home.Heo.deal.condition.SearchCondition;
import com.ssafy.home.Heo.deal.dto.out.DealInfoResponseDto;
import com.ssafy.home.Heo.deal.entity.DealEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;

@Mapper
@Repository
public interface DealDao {
    // 검색조건[시군구 코드+읍면동 코드] + 정렬조건으로 페이지네이션
    List <DealInfoResponseDto> findDealsByCondition(SearchCondition searchCondition) throws SQLException;
    int getDealCount(SearchCondition searchCondition) throws SQLException;

    // aptSeq로 최신 거래내역 10건 조회
    List <DealInfoResponseDto> findTopTenLatestDeals (String aptSeq, int limit) throws  SQLException;
}
