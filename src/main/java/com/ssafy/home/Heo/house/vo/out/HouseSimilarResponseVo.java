package com.ssafy.home.Heo.house.vo.out;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class HouseSimilarResponseVo {
    private String aptSeq; // 아파트코드
    private String aptNm; // 아파트이름
    private String imgUrl;// 집 이미지 경로
    private float latitude; // 위도
    private float longitude; // 경도
}
