package com.ssafy.home.Heo.cache.dto.out;


import com.ssafy.home.Heo.cache.vo.out.SearchWordDetailResponseVo;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SearchWordDetailResponseDto {
    private String searchWord;

    public SearchWordDetailResponseVo toVo(){
        return SearchWordDetailResponseVo.builder().searchWord(searchWord).build();
    }
}
