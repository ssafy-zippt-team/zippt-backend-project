package com.ssafy.home.Heo.house.condition;

import com.ssafy.home.Heo.common.page.PageRequestDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class SearchCondition  extends PageRequestDto {
    @Schema(defaultValue = "11170", description = "시군구코드 (필수)", required = true)
    private String sggCd; // 시군구 코드

    @Schema(defaultValue = "10100", description = "읍면동코드 (선택)", required = false)
    private String umdCd; // 읍면동 코드

    private OrderCondition orderCondition; // 정렬조건

}