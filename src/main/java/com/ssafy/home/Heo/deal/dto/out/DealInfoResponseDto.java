package com.ssafy.home.Heo.deal.dto.out;

import com.ssafy.home.Heo.deal.vo.DealInfoResponseVo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DealInfoResponseDto {
    private String dealDate; // 거래날짜
    private String floor; // 층 수
    private String excluUseAr; // 면적
    private String dealAmount; // 거래 금액

    public static DealInfoResponseVo from (DealInfoResponseDto dto) {
        return DealInfoResponseVo.builder()
                .dealDate(dto.getDealDate())
                .floor(dto.getFloor())
                .excluUseAr(dto.getExcluUseAr())
                .dealAmount(dto.getDealAmount())
                .build();
    }
}
