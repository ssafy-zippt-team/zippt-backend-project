package com.ssafy.home.Heo.adress.dto.out;

import com.ssafy.home.Heo.adress.vo.out.AdressResponseVo;
import com.ssafy.home.Heo.board.dto.out.BoardDetailResponseDto;
import com.ssafy.home.Heo.board.vo.out.BoardDetailResponseVo;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AdressResponseDto {
    // 시군구는 동일하게 조회
    private int adressSeq;                           // 전체 동 코드
    private int citySeq;                             // 시 내부코드
    private int guSeq;                               // 구 내부코드
    private int dongSeq;                             // 동 내부코드
    private String cityName;                         // 시
    private String guName;                           // 구
    private String dongName;                         // 동

    public static AdressResponseVo from (AdressResponseDto dto){
        return AdressResponseVo.builder()
                .adressSeq(dto.getAdressSeq())
                .citySeq(dto.getCitySeq())
                .guSeq(dto.getGuSeq())
                .dongSeq(dto.getDongSeq())
                .cityName(dto.getCityName())
                .guName(dto.getGuName())
                .dongName(dto.getDongName())
                .build();
    }

}
