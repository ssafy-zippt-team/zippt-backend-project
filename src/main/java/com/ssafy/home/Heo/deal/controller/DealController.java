package com.ssafy.home.Heo.deal.controller;

import com.ssafy.home.Heo.common.base.BaseResponse;
import com.ssafy.home.Heo.common.page.PageRequestDto;
import com.ssafy.home.Heo.common.page.PageResponseDto;
import com.ssafy.home.Heo.deal.condition.SearchCondition;
import com.ssafy.home.Heo.deal.dto.out.DealInfoResponseDto;
import com.ssafy.home.Heo.deal.service.DealService;
import com.ssafy.home.Heo.deal.vo.DealInfoResponseVo;
import com.ssafy.home.Heo.house.dto.out.HouseDetailResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

import org.springdoc.core.annotations.ParameterObject;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/api/v1/deals")
public class DealController {

    private final DealService dealService;

    @Operation(summary = "아파트 시퀀스로 거래내역 리스트 조회 [페이지네이션] ",
            description = "apt_seq로 해당 아파트의 거래내역을 최신순 정렬", tags = {"거래내역"})
    @GetMapping("/list/{aptSeq}")
    public PageResponseDto<DealInfoResponseVo>findDealsByCondition(
            @ParameterObject PageRequestDto requestDto,
            @Parameter(description = "아파트 식별자", example = "11110-2224")
            @PathVariable String aptSeq) throws SQLException {

        PageResponseDto<DealInfoResponseDto> dtoPage = dealService.findDealsByCondition(requestDto, aptSeq);
        return PageResponseDto.<DealInfoResponseVo>withAll()
                .dtoList(dtoPage.getDtoList().stream()
                        .map(DealInfoResponseDto::from)
                        .collect(Collectors.toList()))
                .totalCount(dtoPage.getTotalCount())
                .pageRequestDTO(dtoPage.getPageRequestDTO())
                .build();
    }

    @Operation(summary = "최신 거래내역 리스트 조회",
            description = "apt_seq로 해당 아파트의 최신 10개까지의 거래내역 조회", tags = {"거래내역"})
    @GetMapping("/latest-list/{aptSeq}")
    public BaseResponse<List<DealInfoResponseVo>>findTopTenLatestDeals(
            @Parameter(description = "아파트 식별자", example = "11110-2224")
            @PathVariable String aptSeq,
            @Parameter(description = "조회 할 거래내역의 수 (입력 안할 시 10개 조회)", example = "1")
            @RequestParam(required = false) Integer limit
    ) throws SQLException {
        return  BaseResponse.of(
                dealService.findTopTenLatestDeals(aptSeq,limit)
                        .stream().map(DealInfoResponseDto::from).collect(Collectors.toList())
        );
    }

}

