package com.ssafy.home.Heo.commercial.dto.out;

import com.ssafy.home.Heo.commercial.vo.out.CommercialResponseStatVo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Map;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommercialResponseStatDto {
    private Map<String, Integer> categoryCountMap; // 대분류명:개수

        public static CommercialResponseStatVo from(CommercialResponseStatDto dto){
            return CommercialResponseStatVo.builder()
                .categoryCountMap(dto.getCategoryCountMap())
                .build();
        }
}