package com.ssafy.home.Heo.adress.vo.out;

import com.ssafy.home.Heo.adress.dto.out.AdressResponseDto;
import com.ssafy.home.Heo.adress.entity.AdressEntity;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AdressResponseVo {
    // 시군구는 동일하게 조회
    private int adressSeq;                           // 전체 동 코드
    private int citySeq;                             // 시 내부코드
    private int guSeq;                               // 구 내부코드
    private int dongSeq;                             // 동 내부코드
    private String cityName;                         // 시
    private String guName;                           // 구
    private String dongName;                         // 동

}
