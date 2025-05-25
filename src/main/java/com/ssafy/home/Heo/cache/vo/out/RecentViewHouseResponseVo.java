package com.ssafy.home.Heo.cache.vo.out;


import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class RecentViewHouseResponseVo {
    private String aptSeq;
    private String umdNm; // 동 이름
    private String aptNm; // 아파트 이름
    private float latitude; // 위도
    private float longitude; // 경도
    private String jibun; // 지번
    private String roadNm; // 도로명
    private int buildYear; // 준공년도

    private float amountAvg; // 평균 거래가
    private float amountMax; // 최대 거래가
    private float amountMin; // 최저 거래가

}
