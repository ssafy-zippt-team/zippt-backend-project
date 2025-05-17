package com.ssafy.home.Heo.house.dto.out;

import com.ssafy.home.Heo.house.entity.HouseEntity;
import lombok.*;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HouseResponseDto {
    private String aptSeq; // 아파트코드
    private String umdNm; // 읍면동이름
    private String jibun; // 지번
    private String aptNm; // 아파트이름
    private int buildYear; // 준공년도

    // entity -> dto
    public static HouseResponseDto from (HouseEntity entity) {
        return HouseResponseDto.builder()
                .aptSeq(entity.getAptSeq())
                .umdNm(entity.getUmdNm())
                .jibun(entity.getJibun())
                .aptNm(entity.getAptNm())
                .buildYear(entity.getBuildYear())
                .build();
    }
}
