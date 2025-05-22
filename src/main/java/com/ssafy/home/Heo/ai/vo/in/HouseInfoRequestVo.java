package com.ssafy.home.Heo.ai.vo.in;

import com.ssafy.home.Heo.ai.dto.in.HouseInfoRequestDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class HouseInfoRequestVo {
    @Schema(description = "동 이름", example = "명륜1가", defaultValue = "명륜1가")
    private String umdNm; // 동 이름
    @Schema(description = "도로명", example = "성균관로14길", defaultValue = "성균관로14길")
    private String roadNm; // 도로명
    @Schema(description = "아파트 이름", example = "리치캐슬아파트", defaultValue = "리치캐슬아파트")
    private String aptNm; // 아파트 이름
    @Schema(description = "지번", example = "33-85", defaultValue = "33-85")
    private String jibun; // 지번
    @Schema(description = "준공년도", example = "2003", defaultValue = "2003")
    private int buildYear; // 준공년도

    public static HouseInfoRequestDto from(HouseInfoRequestVo vo){
        return HouseInfoRequestDto.builder()
                    .umdNm(vo.getUmdNm())
                    .roadNm(vo.getRoadNm())
                    .aptNm(vo.getAptNm())
                    .jibun(vo.getJibun())
                    .buildYear(vo.getBuildYear())
                    .build();
    }
}
