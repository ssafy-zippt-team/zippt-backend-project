package com.ssafy.home.Heo.commercial.vo.in;


import com.ssafy.home.Heo.bookmark.dto.in.BookmarkSaveDto;
import com.ssafy.home.Heo.commercial.dto.in.CommercialRequestRadiusDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Builder
public class CommercialRequestRadiusVo {
    @Schema(description = "중심점 경도", example = "37.5775989043871")
    private double cx;  // 중심점 경도
    @Schema(description = "중심점 위도", example = "127.010034883981")
    private double cy;  // 중심점 위도
    @Schema(description = "반경(m)", example = "1000")
    private int radius;  // 반경

    public CommercialRequestRadiusDto from(CommercialRequestRadiusVo vo){
        return CommercialRequestRadiusDto.builder()
                .cx(vo.getCx())
                .cy(vo.getCy())
                .radius(vo.getRadius())
                .build();
    }
}
