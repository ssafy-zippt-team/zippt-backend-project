package com.ssafy.home.Heo.commercial.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ssafy.home.Heo.commercial.dto.out.CommercialResponseRadiusDto;
import lombok.Data;

import java.util.List;

@Data
public class CommercialEntity {
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
            private int trarNo;
            private String mainTrarNm;
            private String ctprvnCd;
            private String ctprvnNm;
            private String signguCd;
            private String signguNm;
            private String trarArea;
            private String coordNum;
            private String coords;
            private String stdrDt;

            public static CommercialResponseRadiusDto from(CommercialArea entity){
                return CommercialResponseRadiusDto.builder()
                        .mainTrarNm(entity.getMainTrarNm())
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
