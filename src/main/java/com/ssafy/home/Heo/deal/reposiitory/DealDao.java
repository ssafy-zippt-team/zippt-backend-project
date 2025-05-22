package com.ssafy.home.Heo.deal.reposiitory;

import com.ssafy.home.Heo.common.page.PageRequestDto;
import com.ssafy.home.Heo.deal.condition.OrderCondition;
import com.ssafy.home.Heo.deal.condition.SearchCondition;
import com.ssafy.home.Heo.deal.dto.out.DealAmountStatResponseDto;
import com.ssafy.home.Heo.deal.dto.out.DealInfoResponseDto;
import com.ssafy.home.Heo.deal.entity.DealEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;
import org.apache.ibatis.annotations.Param;

@Mapper
@Repository
public interface DealDao {
    // aptSeq로 거래내역 검색 페이지네이션
    List<DealInfoResponseDto> findDealsByCondition(
            @Param("pageRequest") PageRequestDto pageRequestDto,
            @Param("aptSeq") String aptSeq
    );    int getDealCount(String aptSeq) throws SQLException;

    // aptSeq로 최신 거래내역 10건 조회
    List <DealInfoResponseDto> findTopTenLatestDeals (String aptSeq, int limit) throws  SQLException;
    // 당해 광역시별 매물 평균가격 조회
    DealAmountStatResponseDto amountStatResponseList () throws  SQLException;

    // 당해 광역시별 매물 거래량 조회
    DealAmountStatResponseDto amountStatCntResponseList () throws  SQLException;
}
