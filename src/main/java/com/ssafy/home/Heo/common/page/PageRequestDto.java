package com.ssafy.home.Heo.common.page;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class PageRequestDto {
    @Schema(defaultValue = "1", description = "볼 페이지 번호(1이 첫 페이지)")
    @Builder.Default
    private int page = 1;
    
    @Schema(defaultValue = "10", description = "한 페이지마다 보여줄 컨텐츠의 개수")
    @Builder.Default
    private int size = 10;

    @JsonIgnore
    public int getOffset() {
        return (page - 1) * size;
    }
}
