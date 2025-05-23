package com.ssafy.home.Heo.ai.dto.in;

import lombok.*;

@Builder
@Getter
@ToString
public class HouseInfoRequestDto {
    private String umdNm; // 동 이름
    private String roadNm; // 도로명
    private String aptNm; // 아파트 이름
    private String jibun; // 지번
    private int buildYear; // 준공년도
}
