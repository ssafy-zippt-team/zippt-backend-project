package com.ssafy.home.Heo.house.service;

import com.ssafy.home.Heo.house.dto.out.LookAroundCacheDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;
/**
 * Redis를 활용하여 Look Around 데이터를 캐싱하는 서비스
 * 캐싱 key: lookaround:{sggCd}-{umdCd}
 * value는 json 문자열 형태로 저장
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class RedisHouseServiceImpl implements RedisHouseService {

    private final RedisTemplate<String, LookAroundCacheDto> lookAroundRedisTemplate;

    @Override
    public LookAroundCacheDto getHouseCache(String sggCd, String umdCd) {
        String key = "lookaround:" + sggCd + "-" + umdCd;
        try {
            return lookAroundRedisTemplate.opsForValue().get(key);
        } catch (Exception e) {
            log.error(" Redis 캐시 조회 실패: {}", e.getMessage());

            return null;
        }
    }

    @Override
    public boolean  setHouseCache(String sggCd, String umdCd, LookAroundCacheDto dto) {
        String key = "lookaround:" + sggCd + "-" + umdCd;
        try {
            lookAroundRedisTemplate.opsForValue().set(key, dto);
            return true;
        } catch (Exception e) {
            log.error("Redis 캐시 저장 실패: {}", e.getMessage());
            return false;
        }
    }

}
