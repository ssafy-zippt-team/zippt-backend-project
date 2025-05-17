package com.ssafy.home.Heo.deal.condition;

import com.ssafy.home.Heo.common.page.PageRequestDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class SearchCondition  extends PageRequestDto {
    @Schema(defaultValue = "11170", description = "시군구코드", required = true)
    private String sggCd; // 시군구 코드
    @Schema(defaultValue = "10100", description = "읍면동코드", required = true)
    private String umdCd; // 읍면동 코드
    private OrderCondition orderCondition; // 정렬조건
}