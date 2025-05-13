package com.ssafy.home.Heo.house.dto.out;

import com.ssafy.home.Heo.house.vo.out.HouseMarkerResponseVo;
import lombok.*;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HouseMarkerResponseDto {
    private String aptSeq; // 아파트코드
    private float latitude; // 위도
    private float longitude; // 경도
    @Setter
    private int bookMarkCount; // 즐겨찾기 수
    @Setter
    private float amountAvg; // 매매 가격 평균
    @Setter
    private float amountMax; // 매매 최고가
    @Setter
    private float amountMin; // 매매 최저가

    //  dto → vo
    public static HouseMarkerResponseVo from(HouseMarkerResponseDto dto) {
        return HouseMarkerResponseVo.builder()
                .aptSeq(dto.getAptSeq())
                .latitude(dto.getLatitude())
                .longitude(dto.getLongitude())
                .bookMarkCount(dto.getBookMarkCount())
                .amountAvg(dto.getAmountAvg())
                .amountMax(dto.getAmountMax())
                .amountMin(dto.getAmountMin())
                .build();
    }
}
