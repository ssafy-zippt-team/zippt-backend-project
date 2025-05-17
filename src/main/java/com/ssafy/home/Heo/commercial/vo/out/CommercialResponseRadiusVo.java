package com.ssafy.home.Heo.commercial.vo.out;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CommercialResponseRadiusVo {
    private String mainTrarNm;   // 상권명
    private String ctprvnCd;     // 시도코드
    private String ctprvnNm;     // 시도명
    private String signguCd;     // 시군구코드
    private String signguNm;     // 시군구명
    private String trarArea;     // 면적
    private String coordNum;     // 좌표개수
    private String coords;       // 좌표값 문자열
    private String stdrDt;       // 기준일자
}
