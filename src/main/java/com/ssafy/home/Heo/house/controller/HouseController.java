package com.ssafy.home.Heo.house.controller;

import com.ssafy.home.Heo.common.base.BaseResponse;
import com.ssafy.home.Heo.house.dto.out.HouseDetailResponseDto;
import com.ssafy.home.Heo.house.service.HouseService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;

@RestController
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/api/v1/house")
public class HouseController {
    private final HouseService service;

    @Operation(summary = "테스트", description = "테스트 핸들", tags = {"house"})
    @GetMapping("/")
    public BaseResponse<Void> test(){
        return new BaseResponse(
            "SUCCESS"
        );
    }
    @Operation(summary = "집 정보 조회", description = "아파트시퀀스(apt_seq) 로 매물 상세 조회", tags = {"house"})
    @GetMapping("/{aptSeq}")
    public BaseResponse<HouseDetailResponseDto> findHouseByAptSeq(@PathVariable(name = "aptSeq")String aptSeq )
    throws SQLException {
        return new BaseResponse(
                service.findHouseByAptSeq(aptSeq)
        );
    }

}
