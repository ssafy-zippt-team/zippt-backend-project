package com.ssafy.home.Heo.house.dto.out;

import com.ssafy.home.Heo.house.vo.out.HouseSimilarResponseVo;
import lombok.*;

/**
 * 검색어 및 이미지 유사도로 조회한 검색결과 DTO
 */
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HouseSimilarResponseDto {
    private String aptSeq; // 아파트코드
    private String aptNm; // 아파트이름
    private String imgUrl;// 집 이미지 경로
    private float latitude; // 위도
    private float longitude; // 경도


    public static HouseSimilarResponseVo from(HouseSimilarResponseDto dto){
        return HouseSimilarResponseVo.builder()
                .aptSeq(dto.getAptSeq())
                .aptNm(dto.getAptNm())
                .imgUrl(dto.getImgUrl())
                .latitude(dto.getLatitude())
                .longitude(dto.getLongitude())
                .build();
    }
}
