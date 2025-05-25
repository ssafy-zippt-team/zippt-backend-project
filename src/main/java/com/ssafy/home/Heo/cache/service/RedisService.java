package com.ssafy.home.Heo.cache.service;



import com.ssafy.home.Heo.cache.dto.out.RecentSearchResponseDto;
import com.ssafy.home.Heo.cache.dto.out.RecentViewHouseResponseDto;
import com.ssafy.home.Heo.cache.dto.out.SearchWordDetailResponseDto;

import java.util.Date;
import java.util.List;

public interface RedisService {

    /**
     * 최근검색어
     */
    // 최근 검색어 등록
    void addSearchWord(String memberUuid, String searchWord);

    // 회원의 최근 검색어 조회
    List<SearchWordDetailResponseDto> getRecentSearchDto(String memberUuid);

    // 회원의 개별 최근 검색어 삭제
    void deleteSearchWord(String memberUuid, String searchTerm);

    // 회원의 전체 최근 검색어 삭제
    void deleteUserSearchKey(String memberUuid);


    /**
     * 최근 본 아파트
     */
    // 최근 본 아파트 리스트 (최대 6개)
    List<RecentViewHouseResponseDto> getRecentViewHouseList(String memberUuid);
    // 최근 본 아파트에 추가
    void addRecentViewHouse(String memberUuid, RecentViewHouseResponseDto dto);


}
