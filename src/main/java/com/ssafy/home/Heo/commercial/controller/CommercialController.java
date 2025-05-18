package com.ssafy.home.Heo.commercial.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ssafy.home.Heo.commercial.dto.out.CommercialResponseRadiusDto;
import com.ssafy.home.Heo.commercial.dto.out.CommercialResponseStatDto;
import com.ssafy.home.Heo.commercial.dto.out.CommercialResponseStoreDto;
import com.ssafy.home.Heo.commercial.service.CommercialService;
import com.ssafy.home.Heo.commercial.vo.in.CommercialRequestRadiusVo;
import com.ssafy.home.Heo.commercial.vo.in.CommercialRequestStatVo;
import com.ssafy.home.Heo.commercial.vo.out.CommercialResponseStatVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.sql.SQLException;
import java.util.List;

@RestController
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/api/Commercials")
public class CommercialController {

    private final CommercialService service;

    /* ===========================================================================================
        # 기본 API ( Swagger에서 확인만 할꺼라 Dto까지 )
     =========================================================================================== */
    @Operation(summary = "반경 상권 조회", description = "반경 상권 조회", tags = {"상권"})
    @GetMapping("/radius")
    public ResponseEntity<?> radius( @ParameterObject  CommercialRequestRadiusVo vo) throws SQLException, UnsupportedEncodingException, URISyntaxException, JsonProcessingException {
        log.info("✅ 요청 Vo: {}", vo);
        List<CommercialResponseRadiusDto> list = service.getCommercialInRadius(vo.from(vo));
        log.info("✅ 응답 list: {}", list);
        return ResponseEntity.ok(list);
    }

    @Operation(summary = "상권별 업소 조회", description = "상권별 업소 조회", tags = {"상권"})
    @GetMapping("/storeListInArea/{trarNo}")
    public ResponseEntity<?> storeListInArea(
            @Parameter(description = "trarNo", example = "10355")
            @PathVariable(name = "trarNo")String trarNo
    ) throws SQLException, UnsupportedEncodingException, URISyntaxException, JsonProcessingException {
        List<CommercialResponseStoreDto> list = service.getStoreInCommercial(trarNo);
        System.out.println("list = " + list);
        return ResponseEntity.ok(list);
    }
    /* ===========================================================================================
        # 기본 API END
     =========================================================================================== */
    @Operation(summary = "좌표로 상권내 업종 개수 통계값 조회", description = "좌표로 상권내 업종 개수 통계값 조회", tags = {"상권"})
    @GetMapping("/stat")
    public ResponseEntity<?> getCategoryStatistics(@ParameterObject CommercialRequestStatVo vo) throws Exception {
        log.info("✅ 통계 요청 Vo: {}", vo);
        CommercialResponseStatDto result = service.getCategoryStatistics(vo.from(vo));
        log.info("✅ 통계 응답 Dto: {}", result.getCategoryCountMap() );
        return ResponseEntity.ok(result);
    }



}


