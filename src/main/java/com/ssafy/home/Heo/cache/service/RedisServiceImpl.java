package com.ssafy.home.Heo.cache.service;


import com.ssafy.home.Heo.cache.dto.in.RecentViewHouseRequestDto;
import com.ssafy.home.Heo.cache.dto.out.RecentSearchResponseDto;
import com.ssafy.home.Heo.cache.dto.out.RecentViewHouseResponseDto;
import com.ssafy.home.Heo.cache.dto.out.SearchWordDetailResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
@Log4j2
public class RedisServiceImpl implements  RedisService{

    private final RedisTemplate<String, String> redisTemplate;
    private final RedisTemplate<String, RecentViewHouseResponseDto> recentViewHouseRedisTemplate;

    // 회원의 최대 저장가능한 최근검색어 개수
    private static final int MAX_RECENT_SEARCHES = 5;
    // 회원의 최대 저장가능한 최근 본 아파트 개수
    private static final int MAX_RECENT_VIEW_HOUSE = 6;

    @Override
    public void addSearchWord(String memberUuid, String searchWord) {
        String key = "search:" + memberUuid;
        ListOperations<String, String> listOps = redisTemplate.opsForList();
        // 기존 동일 검색어 삭제 (중복 방지)
        listOps.remove(key, 0, searchWord);
        // 가장 앞에 검색어 추가
        listOps.leftPush(key, searchWord);

        // 5개를 초과하면 오래된 검색어부터 제거
        Long size = listOps.size(key);
        if (size != null && size > MAX_RECENT_SEARCHES) {
            listOps.rightPop(key);
        }
    }

    @Override
    public List<SearchWordDetailResponseDto> getRecentSearchDto(String memberUuid) {
        // 최근 검색어 조회
        return getRecentSearchDetails(memberUuid);
    }

    @Override
    public void deleteSearchWord(String memberUuid, String searchTerm) {
        String key = "search:" + memberUuid;
        ListOperations<String, String> listOps = redisTemplate.opsForList();

        // 특정 검색어 삭제 (첫 번째로 일치하는 항목을 하나만 제거)
        listOps.remove(key, 1, searchTerm);
    }

    @Override
    public void deleteUserSearchKey(String memberUuid) {
        String key = "search:" + memberUuid;
        // 해당 key 삭제
        redisTemplate.delete(key);
    }

    // 최근 검색어 목록을 가져오는 메서드
    public List<SearchWordDetailResponseDto> getRecentSearchDetails(String memberUuid) {
        String key = "search:" + memberUuid;
        ListOperations<String, String> listOps = redisTemplate.opsForList();

        // List의 모든 항목을 가져옴
        List<String> searchWords = listOps.range(key, 0, -1);
        List<SearchWordDetailResponseDto> searchWordDetails = new ArrayList<>();
        if (searchWords != null) {
            for (String word : searchWords) {
                SearchWordDetailResponseDto detail = new SearchWordDetailResponseDto();
                detail.setSearchWord(word);
                searchWordDetails.add(detail);
            }
        }
        return searchWordDetails;
    }

    // 최근 본 아파트 추가 (중복 제거, 6개 제한)
    @Override
    public void addRecentViewHouse(String memberUuid, RecentViewHouseRequestDto dto) {
        String key = "recent:view:house:" + memberUuid;
        ListOperations<String, RecentViewHouseResponseDto> listOps = recentViewHouseRedisTemplate.opsForList();
        // 기존 동일 아파트 제거(중복 방지)
        listOps.remove(key, 0, dto);
        // 제일 앞에 추가
        listOps.leftPush(key, RecentViewHouseResponseDto.builder()
                        .aptSeq(dto.getAptSeq())
                        .umdNm(dto.getUmdNm())
                        .aptNm(dto.getAptNm())
                        .latitude(dto.getLatitude())
                        .longitude(dto.getLongitude())
                        .jibun(dto.getJibun())
                        .roadNm(dto.getRoadNm())
                        .buildYear(dto.getBuildYear())
                        .imgUrl(dto.getImgUrl())
                        .amountAvg(dto.getAmountAvg())
                        .amountMax(dto.getAmountMax())
                        .amountMin(dto.getAmountMin())
                        .build()
        );
        Long size = listOps.size(key);
        // 6개 초과시 잘라냄
        if (size != null && size > MAX_RECENT_VIEW_HOUSE) {
            listOps.rightPop(key);
        }
        log.info("최근 본 아파트 추가: {}", dto);
    }

    @Override
    public List<RecentViewHouseResponseDto> getRecentViewHouseList(String memberUuid) {
        String key = "recent:view:house:" + memberUuid;
        ListOperations<String, RecentViewHouseResponseDto> listOps = recentViewHouseRedisTemplate.opsForList();

        List<RecentViewHouseResponseDto> result = listOps.range(key, 0, MAX_RECENT_VIEW_HOUSE);

        if (result != null && !result.isEmpty()) {
            result.forEach(dto -> log.info("최근 본 아파트: {}", dto));
            return result;
        } else {
            return null; // 빈 리스트 반환
        }
    }

}
