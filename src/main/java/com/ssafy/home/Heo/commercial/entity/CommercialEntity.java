package com.ssafy.home.Heo.commercial.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ssafy.home.Heo.commercial.dto.out.CommercialResponseRadiusDto;
import com.ssafy.home.Heo.commercial.dto.out.CommercialResponseStoreDto;
import lombok.Data;

import java.util.List;

@Data
public class CommercialEntity {
    private Header header; // 응답 헤더 정보
    private Body body;     // 응답 본문 정보

    @Data
    public static class Header {
        private String description; // 응답 설명
        private String resultCode;  // 응답 결과 코드 (00: 성공)
        private String resultMsg;   // 응답 메시지
        private List<String> columns;     // 컬럼 목록 (CSV 문자열)
        private String stdrYm;      // 기준 연월 (예: 202503)
    }

    @Data
    public static class Body {
        @JsonProperty("items")
        private List<Store> StoreList;  // 상가 업소 항목 리스트
        private int numOfRows;      // 페이지당 데이터 수
        private int pageNo;         // 현재 페이지 번호
        private int totalCount;     // 전체 데이터 개수

        @Data
        public static class Store {
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

            public static CommercialResponseStoreDto from(CommercialEntity.Body.Store entity){
                return CommercialResponseStoreDto.builder()
                        .bizesId(entity.getBizesId())
                        .bizesNm(entity.getBizesNm())
                        .brchNm(entity.getBrchNm())
                        .indsLclsCd(entity.getIndsLclsCd())   // 상권업종 대분류 코드
                        .indsLclsNm(entity.getIndsLclsNm())   // 상권업종 대분류명
                        .indsMclsCd(entity.getIndsMclsCd())   // 상권업종 중분류 코드
                        .indsMclsNm(entity.getIndsMclsNm())   // 상권업종 중분류명
                        .indsSclsCd(entity.getIndsSclsCd())   // 상권업종 소분류 코드
                        .indsSclsNm(entity.getIndsSclsNm())   // 상권업종 소분류명
                        .ksicCd(entity.getKsicCd())       // 표준산업분류 코드
                        .ksicNm(entity.getKsicNm())       // 표준산업분류명
                        .lon(entity.getLon())          // 경도
                        .lat(entity.getLat())
                        .build();
            }
        }
    }
}
