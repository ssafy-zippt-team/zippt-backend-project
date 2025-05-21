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
    private String umdNm; // 동이름
    private String aptNm; // 아파트명
    private String imgUrl;
    

    private float latitude; // 위도
    private float longitude; // 경도

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
                .umdNm(dto.getUmdNm())
                .latitude(dto.getLatitude())
                .longitude(dto.getLongitude())
                .aptNm(dto.getAptNm())
                .imgUrl(dto.getImgUrl())
                .amountAvg(dto.getAmountAvg())
                .amountMax(dto.getAmountMax())
                .amountMin(dto.getAmountMin())
                .build();
    }
}
