package com.ssafy.home.Heo.cache.dto.out;


import com.ssafy.home.Heo.cache.vo.out.RecentSearchResponseVo;
import com.ssafy.home.Heo.cache.vo.out.SearchWordDetailResponseVo;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RecentSearchResponseDto {
    private String memberUuid;
    private List<SearchWordDetailResponseDto> searchWordDetails;

    public RecentSearchResponseVo toVo(){
        List<SearchWordDetailResponseVo> details = searchWordDetails != null ?
                searchWordDetails.stream().map(SearchWordDetailResponseDto::toVo).toList() : new ArrayList<>();
        return RecentSearchResponseVo.builder()
                .memberUuid(memberUuid)
                .searchWordDetails(details)
                .build();
    }
}
