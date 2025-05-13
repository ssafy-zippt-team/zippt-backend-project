package com.ssafy.home.Heo.house.vo.out;

import lombok.*;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HouseResponseVo {
    private String aptSeq; // 아파트코드
    private String umdNm; // 읍면동이름
    private String jibun; // 지번
    private String aptNm; // 아파트이름
    private int buildYear; // 준공년도
    private float latitude; // 위도
    private float longitude; // 경도
    private String imgUrl; // 이미지 url
}
