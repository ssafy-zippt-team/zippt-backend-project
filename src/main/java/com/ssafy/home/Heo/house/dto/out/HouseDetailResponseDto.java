package com.ssafy.home.Heo.house.dto.out;

import com.ssafy.home.Heo.house.vo.out.HouseDetailResponseVo;
import lombok.*;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HouseDetailResponseDto {
    private String aptSeq; // 아파트코드
    private String sggCd; // 시군구코드
    private String umdCd; // 읍면동코드
    private String umdNm; // 읍면동이름
    private String jibun; // 지번
    private String roadNmSggCd; // 도로명시군구코드
    private String roadNm; // 도로명
    private String roadNmBonbun; // 도로명기초번호
    private String roadNmBubun; // 도로명추가번호
    private String aptNm; // 아파트이름
    private int buildYear; // 준공년도
    private float latitude; // 위도
    private float longitude; // 경도

    public static HouseDetailResponseVo from (HouseDetailResponseDto dto){
        return HouseDetailResponseVo.builder()
                .aptSeq(dto.getAptSeq())
                .sggCd(dto.getSggCd())
                .umdCd(dto.getUmdCd())
                .umdNm(dto.getUmdNm())
                .jibun(dto.getJibun())
                .roadNmBonbun(dto.getRoadNmBonbun())
                .roadNm(dto.getRoadNm())
                .roadNmBonbun(dto.getRoadNmBonbun())
                .roadNmBubun(dto.getRoadNmBubun())
                .aptNm(dto.getAptNm())
                .buildYear(dto.getBuildYear())
                .latitude(dto.getLatitude())
                .longitude(dto.getLongitude())
                .build();
    }
}
