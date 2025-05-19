package com.ssafy.home.Heo.commercial.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ssafy.home.Heo.commercial.dto.out.CommercialResponseRadiusDto;
import lombok.Data;

import java.util.List;

@Data
public class RadiusEntity {
    private Header header;
    private Body body;

    @Data
    public static class Header {
        private String description;
        private String resultCode;
        private String resultMsg;
    }

    @Data
    public static class Body {
        @JsonProperty("items")  // 여기가 중요!
        private List<CommercialArea> items;

        @Data
        public static class CommercialArea {
            private String trarNo;             // 상권번호
            private String mainTrarNm;      // 상권명
            private String ctprvnCd;        // 시도코드
            private String ctprvnNm;        // 시도명
            private String signguCd;        // 시군구코드
            private String signguNm;        // 시군구명
            private String trarArea;        // 면적
            private String coordNum;        // 좌표개수
            private String coords;          // 좌표값
            private String stdrDt;          // 데이터기준일자

            public static CommercialResponseRadiusDto from(CommercialArea entity){
                return CommercialResponseRadiusDto.builder()
                        .mainTrarNm(entity.getMainTrarNm())
                        .trarNo(entity.getTrarNo())
                        .ctprvnCd(entity.getCtprvnCd())
                        .ctprvnNm(entity.getCtprvnNm())
                        .signguCd(entity.getSignguCd())
                        .signguNm(entity.getSignguNm())
                        .trarArea(entity.getTrarArea())
                        .coordNum(entity.getCoordNum())
                        .coords(entity.getCoords())
                        .stdrDt(entity.getStdrDt())
                        .build();
            }
        }
    }
}
