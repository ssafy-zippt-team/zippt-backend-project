package com.ssafy.home.Heo.deal.vo;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class DealAmountStatResponseVo {
    private double seoul;
    private double busan;
    private double ulsan;
    private double daegu;
    private double incheon;
    private double gwangju;
    private double daejeon;
}
