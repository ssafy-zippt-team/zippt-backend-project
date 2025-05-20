package com.ssafy.home.Heo.house.vo.out;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class HouseMarkerResponseVo {
    private String aptSeq;
    private String umdNm; // 동이름
    private String aptNm; // 아파트명
    private String imgUrl;
    private float latitude;
    private float longitude;
    private float amountAvg;
    private float amountMax;
    private float amountMin;
}
