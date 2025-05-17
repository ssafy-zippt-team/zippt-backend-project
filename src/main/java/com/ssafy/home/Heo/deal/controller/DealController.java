package com.ssafy.home.Heo.deal.controller;

import com.ssafy.home.Heo.common.base.BaseResponse;
import com.ssafy.home.Heo.common.page.PageResponseDto;
import com.ssafy.home.Heo.deal.condition.SearchCondition;
import com.ssafy.home.Heo.deal.dto.out.DealInfoResponseDto;
import com.ssafy.home.Heo.deal.service.DealService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

import org.springdoc.core.annotations.ParameterObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;


@RestController
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/api/v1/deals")
public class DealController {

    private final DealService dealService;

    /**
     *  거래내역 리스트 필터링[시군구코드, 읍면동코드]  , 정렬조건[최신순,오래된순,높은가격,낮은가격]
     */
    @Operation(summary = "거래내역 리스트 조회 [필터링, 정렬] ",
            description = "필터링[시군구코드, 읍면동코드] 정렬[최신순,오래된순,높은가격,낮은가격]", tags = {"거래내역"})
    @GetMapping("/list")
    public PageResponseDto<DealInfoResponseDto>searchProductIdsV1(
            @ParameterObject SearchCondition searchCondition) throws SQLException {
        return dealService.findDealsByCondition(searchCondition);
    }

}

