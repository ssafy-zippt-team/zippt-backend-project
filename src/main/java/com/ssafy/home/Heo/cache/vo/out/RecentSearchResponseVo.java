package com.ssafy.home.Heo.cache.vo.out;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class RecentSearchResponseVo {
    private String memberUuid;
    private List<SearchWordDetailResponseVo> searchWordDetails;
}
