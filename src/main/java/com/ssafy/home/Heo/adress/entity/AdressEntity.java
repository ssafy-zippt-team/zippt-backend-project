package com.ssafy.home.Heo.adress.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AdressEntity {
    private int adressSeq;                           // 전체 동 코드
    private int citySeq;                             // 시 내부코드
    private int guSeq;                               // 구 내부코드
    private int dongSeq;                             // 동 내부코드
    private String cityName;                         // 시
    private String guName;                           // 구
    private String dongName;                         // 동
}
