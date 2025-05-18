package com.ssafy.home.Heo.commercial.vo.out;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Map;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommercialResponseStatVo {
    private Map<String, Integer> categoryCountMap; // 대분류명:개수
}