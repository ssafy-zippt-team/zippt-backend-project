package com.ssafy.home.Heo.ai.controller;

import com.ssafy.home.Heo.ai.dto.in.HouseInfoRequestDto;
import com.ssafy.home.Heo.ai.service.HouseSummaryService;
import com.ssafy.home.Heo.ai.vo.in.HouseInfoRequestVo;
import com.ssafy.home.Heo.commercial.dto.out.CommercialResponseStatDto;
import com.ssafy.home.Heo.common.base.BaseResponse;
import com.ssafy.home.Heo.house.dto.out.HouseDetailResponseDto;
import com.ssafy.home.Heo.house.vo.out.HouseDetailResponseVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/api/v1/ai")
@Tag(name = "AI")
public class AiController {
    private final HouseSummaryService service;

    @Operation(summary = "아파트 요약 및 평가 HTML 조회", description = "aptSeq 기반으로 AI가 HTML 형식으로 요약 제공")
    @GetMapping("/summary/{aptSeq}")
    public BaseResponse<String> getHouseSummary(@Parameter(example = "11110-128") @PathVariable String aptSeq,
                                                @Parameter(description = "집에 대한 간단한 정보")
                                                @ParameterObject HouseInfoRequestVo houseInfoRequestVo,
                                                @Parameter(example = "{\"보건의료\":1,\"소매\":7,\"교육\":1,\"숙박\":2,\"예술·스포츠\":4,\"과학·기술\":5,\"음식\":30,\"부동산\":4,\"시설관리·임대\":3,\"수리·개인\":3}")
                                                String commercialInfo
                                                ) {

        return BaseResponse.of(
                service.generateHouseSummaryHtml(aptSeq, HouseInfoRequestVo.from(houseInfoRequestVo), commercialInfo)
        );
    }

}
