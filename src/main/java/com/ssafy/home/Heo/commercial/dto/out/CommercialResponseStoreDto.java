package com.ssafy.home.Heo.commercial.dto.out;


import lombok.*;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommercialResponseStoreDto {
    private String bizesId;      // 상가업소 고유번호
    private String bizesNm;      // 상호명
    private String brchNm;       // 지점명
    private String indsLclsCd;   // 상권업종 대분류 코드
    private String indsLclsNm;   // 상권업종 대분류명
    private String indsMclsCd;   // 상권업종 중분류 코드
    private String indsMclsNm;   // 상권업종 중분류명
    private String indsSclsCd;   // 상권업종 소분류 코드
    private String indsSclsNm;   // 상권업종 소분류명
    private String ksicCd;       // 표준산업분류 코드
    private String ksicNm;       // 표준산업분류명

    /* 필요 없는 부분 주석
//            private String ctprvnCd;     // 시도 코드
//            private String ctprvnNm;     // 시도명
//            private String signguCd;     // 시군구 코드
//            private String signguNm;     // 시군구명
//            private String adongCd;      // 행정동 코드
//            private String adongNm;      // 행정동명
//            private String ldongCd;      // 법정동 코드
//            private String ldongNm;      // 법정동명
//            private String lnoCd;        // PNU 코드 (토지 고유번호)
//            private String plotSctCd;    // 대지구분 코드
//            private String plotSctNm;    // 대지구분명 (예: 대지, 도로 등)
//            private String lnoMnno;      // 지번 본번지
//            private String lnoSlno;      // 지번 부번지
//            private String lnoAdr;       // 지번 주소
//            private String rdnmCd;       // 도로명 코드
//            private String rdnm;         // 도로명
//            private String bldMnno;      // 건물 본번지
//            private String bldSlno;      // 건물 부번지
//            private String bldMngNo;     // 건물 관리번호
//            private String bldNm;        // 건물명
//            private String rdnmAdr;      // 도로명 주소
//            private String oldZipcd;     // 구 우편번호
//            private String newZipcd;     // 신 우편번호
//            private String dongNo;       // 동 정보
//            private String flrNo;        // 층 정보
//            private String hoNo;         // 호 정보
*/
    private String lon;          // 경도
    private String lat;          // 위도

}
