package com.ssafy.home.Heo.deal.vo;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class DealInfoResponseVo {
    private String dealDate; // 거래날짜
    private String floor; // 층 수
    private String excluUseAr; // 면적
    private String dealAmount; // 거래 금액
}
