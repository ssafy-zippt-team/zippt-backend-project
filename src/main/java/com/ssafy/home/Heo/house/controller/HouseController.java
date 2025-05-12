package com.ssafy.home.Heo.house.controller;

import com.ssafy.home.Heo.common.base.BaseResponse;
import com.ssafy.home.Heo.common.page.PageRequestDto;
import com.ssafy.home.Heo.common.page.PageResponseDto;
import com.ssafy.home.Heo.deal.dto.out.DealInfoResponseDto;
import com.ssafy.home.Heo.deal.vo.DealInfoResponseVo;
import com.ssafy.home.Heo.house.condition.SearchCondition;
import com.ssafy.home.Heo.house.dto.out.HouseDetailResponseDto;
import com.ssafy.home.Heo.house.dto.out.HouseResponseDto;
import com.ssafy.home.Heo.house.vo.out.HouseResponseVo;
import com.ssafy.home.Heo.house.service.HouseService;
import com.ssafy.home.Heo.house.vo.out.HouseDetailResponseVo;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.stream.Collectors;

@RestController
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/api/v1/houses")
public class HouseController {
    private final HouseService service;

    @Operation(summary = "테스트", description = "테스트 핸들", tags = {"house"})
    @GetMapping("/")
    public BaseResponse<String> test(){
        return  BaseResponse.of(
            "SUCCESS"
        );
    }
    @Operation(summary = "집 정보 조회", description = "아파트시퀀스(apt_seq) 로 매물 상세 조회", tags = {"house"})
    @GetMapping("/{aptSeq}")
    public BaseResponse<HouseDetailResponseVo> findHouseByAptSeq(@PathVariable(name = "aptSeq")String aptSeq)
    throws SQLException {
        return  BaseResponse.of(
                HouseDetailResponseDto.from(service.findHouseByAptSeq(aptSeq))
        );
    }
    @GetMapping("/list")
    public PageResponseDto<HouseResponseDto> list(@ParameterObject PageRequestDto pageRequestDto) throws SQLException {
        return service.getHouseList(pageRequestDto);
    }

    /**
     *  거래내역 리스트 필터링[시군구코드, 읍면동코드]  , 정렬조건[최신순,오래된순,높은가격,낮은가격]
     */
    @Operation(summary = "아파트 리스트 조회 [필터링, 정렬] ",
            description = "필터링[시군구코드+읍면동코드] 정렬[준공년도, 북마크(미구현) ]", tags = {"house"})
    @GetMapping("/condition-list")
    public  PageResponseDto<HouseResponseDto> findHousesByCondition(
            @ParameterObject SearchCondition searchCondition) throws SQLException {
        return service.findHousesByCondition(searchCondition);
    }

}
