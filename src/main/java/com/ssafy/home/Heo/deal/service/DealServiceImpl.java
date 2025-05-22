package com.ssafy.home.Heo.deal.service;

import com.ssafy.home.Heo.common.page.PageRequestDto;
import com.ssafy.home.Heo.common.page.PageResponseDto;
import com.ssafy.home.Heo.deal.condition.SearchCondition;
import com.ssafy.home.Heo.deal.dto.out.DealAmountStatResponseDto;
import com.ssafy.home.Heo.deal.dto.out.DealInfoResponseDto;
import com.ssafy.home.Heo.deal.entity.DealEntity;
import com.ssafy.home.Heo.deal.reposiitory.DealDao;
import com.ssafy.home.Heo.house.dto.out.HouseResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class DealServiceImpl implements DealService{
    private final DealDao dao;

    @Override
    public PageResponseDto<DealInfoResponseDto> findDealsByCondition(PageRequestDto pageRequestDto, String aptSeq) throws SQLException {
        List <DealInfoResponseDto> list = dao.findDealsByCondition(pageRequestDto, aptSeq);
        int totalCount = dao.getDealCount(aptSeq);
        return PageResponseDto.<DealInfoResponseDto> withAll()
                .dtoList(list)
                .totalCount(totalCount)
                .pageRequestDTO(pageRequestDto)
                .build();
    }

    @Override
    public List<DealInfoResponseDto> findTopNLatestDeals(String aptSeq, Integer limit) throws SQLException {
        if(limit == null) limit = 10;

        return dao.findTopTenLatestDeals(aptSeq,limit);
    }
    // 당해 광역시별 매물 평균가격 조회
    @Override
    public DealAmountStatResponseDto amountStatResponseList () throws SQLException {
        return dao.amountStatResponseList();
    }
    // 당해 광역시별 매물 거래량 조회
    @Override
    public DealAmountStatResponseDto amountStatCntResponseList () throws SQLException {
        return dao.amountStatCntResponseList();
    }

}
