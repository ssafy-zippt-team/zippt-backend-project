package com.ssafy.home.Heo.house.service;

import com.ssafy.home.Heo.house.dto.out.LookAroundCacheDto;

public interface RedisHouseService {
    LookAroundCacheDto getHouseCache(String sggCd, String umdCd);

    //
    boolean  setHouseCache(String sggCd, String umdCd, LookAroundCacheDto cacheDto);
}
