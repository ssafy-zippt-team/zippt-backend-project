package com.ssafy.home.Heo.house.vo.out;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class HouseDetailResponseVo {
    private String jibun; // 지번
    private String roadNm; // 도로명
    private String roadNmBonbun; // 도로명기초번호
    private String roadNmBubun; // 도로명추가번호
    private int buildYear; // 준공년도


}
