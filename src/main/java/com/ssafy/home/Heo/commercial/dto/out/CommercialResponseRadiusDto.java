package com.ssafy.home.Heo.commercial.dto.out;


import lombok.*;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommercialResponseRadiusDto {
    private String description ; // 데이터 설명
    private String columns;      // 컬럼
    private String resultCode;   // 결과코드
    private String resultMsg;    // 결과메세지
    private String trarNo;       // 상권번호
    private String mainTrarNm;   // 상권명
    private String ctprvnCd;     // 시도코드
    private String ctprvnNm;     // 시도명
    private String signguCd;     // 시군구코드
    private String signguNm;     // 시군구명
    private String trarArea;     // 면적
    private String coordNum;     // 좌표개수
    private String coords;       // 좌표값 문자열
    private String stdrDt;       // 기준일자

//    public static CommercialResponseRadiusVo from(CommercialResponseRadiusDto dto){
//        return CommercialResponseRadiusVo.builder()
//                .mainTrarNm(dto.getMainTrarNm())
//                .ctprvnCd(dto.getCtprvnCd())
//                .ctprvnNm(dto.getCtprvnNm())
//                .signguCd(dto.getSignguCd())
//                .signguNm(dto.getSignguNm())
//                .trarArea(dto.getTrarArea())
//                .coordNum(dto.getCoordNum())
//                .coords(dto.getCoords())
//                .stdrDt(dto.getStdrDt())
//                .build();
//    }

}
