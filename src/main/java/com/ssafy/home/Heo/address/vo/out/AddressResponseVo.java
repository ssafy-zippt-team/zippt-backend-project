package com.ssafy.home.Heo.address.vo.out;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddressResponseVo {
    // 시군구는 동일하게 조회
    private String adressSeq;                           // 전체 동 코드
    private String citySeq;                             // 시 내부코드
    private String guSeq;                               // 구 내부코드
    private String dongSeq;                             // 동 내부코드
    private String cityName;                            // 시
    private String guName;                              // 구
    private String dongName;                            // 동

}
