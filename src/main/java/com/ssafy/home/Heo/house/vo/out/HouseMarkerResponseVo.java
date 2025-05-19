package com.ssafy.home.Heo.house.vo.out;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class HouseMarkerResponseVo {
    private String aptSeq;
    private float latitude;
    private float longitude;
    private int bookMarkCount;
    private float amountAvg;
    private float amountMax;
    private float amountMin;
}
