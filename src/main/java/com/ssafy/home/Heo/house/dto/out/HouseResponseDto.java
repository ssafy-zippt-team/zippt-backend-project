package com.ssafy.home.Heo.house.dto.out;

import com.ssafy.home.Heo.house.entity.HouseEntity;
import com.ssafy.home.Heo.house.vo.out.HouseResponseVo;
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
    private float latitude; // 위도
    private float longitude; // 경도
    private String imgUrl; // 이미지 url

    // entity -> dto
    public static HouseResponseDto from (HouseEntity entity) {
        return HouseResponseDto.builder()
                .aptSeq(entity.getAptSeq())
                .umdNm(entity.getUmdNm())
                .jibun(entity.getJibun())
                .aptNm(entity.getAptNm())
                .buildYear(entity.getBuildYear())
                .latitude(entity.getLatitude())
                .longitude(entity.getLongitude())
                .imgUrl(entity.getImgUrl())
                .build();
    }

    // dto -> vo
    public static HouseResponseVo from(HouseResponseDto dto){
        return HouseResponseVo.builder()
                .aptSeq(dto.getAptSeq())
                .umdNm(dto.getUmdNm())
                .jibun(dto.getJibun())
                .aptNm(dto.getAptNm())
                .buildYear(dto.getBuildYear())
                .latitude(dto.getLatitude())
                .longitude(dto.getLongitude())
                .imgUrl(dto.getImgUrl())
                .build();
    }
}
