package com.ssafy.home.Heo.deal.service;

import com.ssafy.home.Heo.common.page.PageRequestDto;
import com.ssafy.home.Heo.common.page.PageResponseDto;
import com.ssafy.home.Heo.deal.condition.SearchCondition;
import com.ssafy.home.Heo.deal.dto.out.DealInfoResponseDto;
import com.ssafy.home.Heo.deal.entity.DealEntity;

import java.sql.SQLException;

public interface DealService {
    PageResponseDto<DealInfoResponseDto> findDealsByCondition(
                                                              SearchCondition searchCondition) throws SQLException;
}
