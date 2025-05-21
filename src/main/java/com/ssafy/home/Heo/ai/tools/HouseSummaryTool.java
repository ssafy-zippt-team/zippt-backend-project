package com.ssafy.home.Heo.ai.tools;

import com.ssafy.home.Heo.deal.dto.out.DealInfoResponseDto;
import com.ssafy.home.Heo.deal.service.DealService;
import com.ssafy.home.Heo.review.dto.out.ReviewSimpleResponseDto;
import com.ssafy.home.Heo.review.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.List;

/**
 * 아파트 요약 및 평가에 사용하는 툴
 */
@Component
@RequiredArgsConstructor
public class HouseSummaryTool {
    private final DealService dealService;
    private final ReviewService reviewService;
    private final int SIZE = 50;
    @Tool(description = "평가 및 요약 대상인 집(아파트)의 최신 거래내역 50건 정보를 반환한다.")
    public List<DealInfoResponseDto> findTopTenLatestDeals(String aptSeq) throws SQLException {
        return dealService.findTopNLatestDeals(aptSeq, SIZE);
    }

    @Tool(description = "평가 및 요약 대상인 집(아파트)의 최신 리뷰 50건 정보를 반환한다.")
    public List<ReviewSimpleResponseDto> getNReviewListByAptSeq(String aptSeq) throws SQLException {
        return reviewService.getNReviewListByAptSeq(aptSeq, SIZE);
    }


    
    

}
