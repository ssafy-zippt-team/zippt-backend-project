package com.ssafy.home.Heo.house.dto.out;

import lombok.*;

@Getter
@Builder
public class HouseAmountResponseDto
{
    private float amountAvg; // 매매 가격 평균
    private float amountMax; // 매매 최고가
    private float amountMin; // 매매 최저가
}
