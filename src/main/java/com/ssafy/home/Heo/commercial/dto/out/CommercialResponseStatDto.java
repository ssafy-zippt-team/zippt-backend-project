package com.ssafy.home.Heo.commercial.dto.out;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Map;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CommercialResponseStatDto {
    private Map<String, Integer> categoryCountMap; // 대분류명:개수
}