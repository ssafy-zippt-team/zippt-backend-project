package com.ssafy.home.Heo.house.service;

import com.ssafy.home.Heo.house.dto.out.LookAroundCacheDto;

public interface RedisHouseService {
    // Redis에서 LookAroundCacheDto 객체로 반환
    LookAroundCacheDto getLookAroundCache(String sggCd, String umdCd);

    // Redis에 LookAroundCacheDto 저장
    boolean  setLookAroundCache(String sggCd, String umdCd, LookAroundCacheDto cacheDto);
}
