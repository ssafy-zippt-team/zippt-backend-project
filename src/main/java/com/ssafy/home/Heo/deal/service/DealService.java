package com.ssafy.home.Heo.deal.service;

import com.ssafy.home.Heo.common.page.PageRequestDto;
import com.ssafy.home.Heo.common.page.PageResponseDto;
import com.ssafy.home.Heo.deal.condition.SearchCondition;
import com.ssafy.home.Heo.deal.dto.out.DealInfoResponseDto;
import com.ssafy.home.Heo.deal.entity.DealEntity;

import java.sql.SQLException;
import java.util.List;

public interface DealService {
    PageResponseDto<DealInfoResponseDto> findDealsByCondition(PageRequestDto dto , String aptSeq) throws SQLException;

    // aptSeq로 최신 거래내역 n건 조회
    List<DealInfoResponseDto> findTopNLatestDeals (String aptSeq, Integer limit) throws  SQLException;
}
