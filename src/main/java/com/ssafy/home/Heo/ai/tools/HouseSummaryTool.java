package com.ssafy.home.Heo.ai.tools;

import com.ssafy.home.Heo.deal.service.DealService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * 아파트 요약 및 평가에 사용하는 툴
 */
@Component
@RequiredArgsConstructor
public class HouseSummaryTool {
    private final DealService dealService;

}
