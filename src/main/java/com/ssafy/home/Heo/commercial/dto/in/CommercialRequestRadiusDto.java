package com.ssafy.home.Heo.commercial.dto.in;


import lombok.*;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommercialRequestRadiusDto {
    private double cx;  // 중심점 경도
    private double cy;  // 중심점 위도
    private int radius;  // 반경
}
