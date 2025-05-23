package com.ssafy.home.Heo.deal.dto.out;

import com.ssafy.home.Heo.deal.vo.DealAmountStatResponseVo;
import com.ssafy.home.Heo.deal.vo.DealInfoResponseVo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DealAmountStatResponseDto {
    private double seoul;
    private double busan;
    private double ulsan;
    private double daegu;
    private double incheon;
    private double gwangju;
    private double daejeon;

    public static DealAmountStatResponseVo from (DealAmountStatResponseDto dto) {
        return DealAmountStatResponseVo.builder()
                .seoul(dto.getSeoul())
                .busan(dto.getBusan())
                .ulsan(dto.getUlsan())
                .daegu(dto.getDaegu())
                .incheon(dto.getIncheon())
                .gwangju(dto.getGwangju())
                .daejeon(dto.getDaejeon())
                .build();
    }
}


