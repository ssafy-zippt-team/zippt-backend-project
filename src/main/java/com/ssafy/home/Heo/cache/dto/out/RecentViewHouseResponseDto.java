package com.ssafy.home.Heo.cache.dto.out;


import com.ssafy.home.Heo.cache.vo.out.RecentSearchResponseVo;
import com.ssafy.home.Heo.cache.vo.out.RecentViewHouseResponseVo;
import com.ssafy.home.Heo.cache.vo.out.SearchWordDetailResponseVo;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class RecentViewHouseResponseDto {
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

    // VO → DTO 변환
    public static RecentViewHouseResponseDto from(RecentViewHouseResponseVo vo) {
        return RecentViewHouseResponseDto.builder()
                .aptSeq(vo.getAptSeq())
                .umdNm(vo.getUmdNm())
                .aptNm(vo.getAptNm())
                .latitude(vo.getLatitude())
                .longitude(vo.getLongitude())
                .jibun(vo.getJibun())
                .roadNm(vo.getRoadNm())
                .buildYear(vo.getBuildYear())
                .amountAvg(vo.getAmountAvg())
                .amountMax(vo.getAmountMax())
                .amountMin(vo.getAmountMin())
                .build();
    }

    // DTO → VO 변환
    public static RecentViewHouseResponseVo from(RecentViewHouseResponseDto dto) {
        return RecentViewHouseResponseVo.builder()
                .aptSeq(dto.getAptSeq())
                .umdNm(dto.getUmdNm())
                .aptNm(dto.getAptNm())
                .latitude(dto.getLatitude())
                .longitude(dto.getLongitude())
                .jibun(dto.getJibun())
                .roadNm(dto.getRoadNm())
                .buildYear(dto.getBuildYear())
                .amountAvg(dto.getAmountAvg())
                .amountMax(dto.getAmountMax())
                .amountMin(dto.getAmountMin())
                .build();
    }
}
