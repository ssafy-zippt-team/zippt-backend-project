package com.ssafy.home.Heo.ai.controller;

import com.ssafy.home.Heo.ai.service.HouseSummaryService;
import com.ssafy.home.Heo.common.base.BaseResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
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
    public BaseResponse<String> getHouseSummary(@PathVariable String aptSeq) {

        return BaseResponse.of(
                service.generateHouseSummaryHtml(aptSeq)
        );
    }

}
