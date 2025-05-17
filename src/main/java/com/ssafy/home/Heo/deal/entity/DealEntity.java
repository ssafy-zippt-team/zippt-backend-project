package com.ssafy.home.Heo.deal.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DealEntity {
    private int no; // 거래번호
    private String aptSeq; // 아파트코드
    private String aptDong; // 아파트 동
    private int floor;
    private int dealYear;
    private int dealMonth;
    private int dealDay;
    private float excluUseAr; // 아파트면적
    private int deal_amount; // 거래금액

    @Builder
    public DealEntity(String aptSeq, String aptDong, int floor, int dealYear, int dealMonth, int dealDay,
                      float excluUseAr, int deal_amount) {
        this.aptSeq = aptSeq;
        this.aptDong = aptDong;
        this.floor = floor;
        this.dealYear = dealYear;
        this.dealMonth = dealMonth;
        this.dealDay = dealDay;
        this.excluUseAr = excluUseAr;
        this.deal_amount = deal_amount;
    }
}
