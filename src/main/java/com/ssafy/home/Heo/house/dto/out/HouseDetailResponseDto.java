package com.ssafy.home.Heo.house.dto.out;

import com.ssafy.home.Heo.house.entity.HouseEntity;
import com.ssafy.home.Heo.house.vo.out.HouseDetailResponseVo;
import lombok.*;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HouseDetailResponseDto {
    private String umdNm;
    private String aptNm;
    private String imgUrl;
    private float latitude; // 위도
    private float longitude; // 경도

    private String jibun; // 지번
    private String roadNm; // 도로명
    private String roadNmBonbun; // 도로명기초번호
    private String roadNmBubun; // 도로명추가번호
    private int buildYear; // 준공년도

    private float amountAvg;
    private float amountMax;
    private float amountMin;

    // dto -> vo
    public static HouseDetailResponseVo from (HouseDetailResponseDto dto){
        return HouseDetailResponseVo.builder()
                .umdNm(dto.getUmdNm())
                .aptNm(dto.getAptNm())
                .imgUrl(dto.getImgUrl())
                .latitude(dto.getLatitude())
                .longitude(dto.getLongitude())
                .jibun(dto.getJibun())
                .roadNm(dto.getRoadNm())
                .roadNmBonbun(dto.getRoadNmBonbun())
                .roadNmBubun(dto.getRoadNmBubun())
                .buildYear(dto.getBuildYear())
                .amountMax(dto.getAmountMax())
                .amountMin(dto.getAmountMin())
                .amountAvg(dto.getAmountAvg())
                .build();
    }

    // entity -> dto
    public static HouseDetailResponseDto of (HouseEntity entity, HouseAmountResponseDto houseAmountResponseDto) {
        return HouseDetailResponseDto.builder()
                .umdNm(entity.getUmdNm())
                .aptNm(entity.getAptNm())
                .imgUrl(entity.getImgUrl())
                .latitude(entity.getLatitude())
                .longitude(entity.getLongitude())
                .jibun(entity.getJibun())
                .roadNm(entity.getRoadNm())
                .roadNmBonbun(entity.getRoadNmBonbun())
                .roadNmBubun(entity.getRoadNmBubun())
                .buildYear(entity.getBuildYear())
                .amountMax(houseAmountResponseDto.getAmountMax())
                .amountMin(houseAmountResponseDto.getAmountMin())
                .amountAvg(houseAmountResponseDto.getAmountAvg())
                .build();

    }
}
