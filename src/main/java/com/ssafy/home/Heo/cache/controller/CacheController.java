package com.ssafy.home.Heo.cache.controller;

import com.ssafy.home.Heo.cache.dto.out.RecentViewHouseResponseDto;
import com.ssafy.home.Heo.cache.service.RedisService;
import com.ssafy.home.Heo.cache.vo.out.RecentSearchResponseVo;
import com.ssafy.home.Heo.cache.vo.out.RecentViewHouseResponseVo;
import com.ssafy.home.Heo.cache.vo.out.SearchWordDetailResponseVo;
import com.ssafy.home.Heo.common.base.BaseResponse;
import com.ssafy.home.Heo.security.dto.out.CustomUserDetails;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/api/v1/cache")
@Tag(name = "캐시", description = "캐싱 관련  API")
public class CacheController {
    private final RedisService redisService;
    /**
     *  최근검색어
     */
    @Operation(summary = "최근 검색어 등록", description = "최근 검색어 등록")
    @PostMapping("/search/word")
    public BaseResponse<Void> addSearchWordV1(
            @AuthenticationPrincipal CustomUserDetails user,  @RequestParam String searchWord ){
        redisService.addSearchWord(user.getUserUuid(), searchWord);
        return BaseResponse.ok();
    }
    @Operation(summary = "최근 검색어 조회", description = "회원의 최근 검색어 조회")
    @GetMapping("/search/list")
    public BaseResponse<List<SearchWordDetailResponseVo>> getRecentSearchV1(
            @AuthenticationPrincipal CustomUserDetails user ){
        return new BaseResponse<>(
                redisService.getRecentSearchDto(user.getUserUuid()).stream()
                        .map(dto->
                        SearchWordDetailResponseVo.builder().searchWord(dto.getSearchWord()).build())
                        .toList()
                );
    }
    @Operation(summary = "최근 검색어 개별 삭제", description = "회원의 최근 검색어 개별 삭제")
    @DeleteMapping("/search/word")
    public BaseResponse<Void> deleteSearchWordV1(
            @AuthenticationPrincipal CustomUserDetails user ,@RequestParam String searchWord ){
        redisService.deleteSearchWord(user.getUserUuid(), searchWord);
        return BaseResponse.ok();
    }
    @Operation(summary = "최근 검색어 전체 삭제", description = "회원의 최근 검색어 전체 삭제")
    @DeleteMapping("/search/word/all")
    public BaseResponse<Void> deleteUserSearchKeyV1(
            @AuthenticationPrincipal CustomUserDetails user){
        redisService.deleteUserSearchKey(user.getUserUuid());
        return BaseResponse.ok();
    }

    /**
     *  최근 본 아파트
     */
    @Operation(summary = "최근 본 아파트 추가", description = "회원의 최근 본 아파트 추가")
    @PostMapping("/recent-view-house")
    public BaseResponse<Void> addRecentViewHouse(
            @ParameterObject RecentViewHouseResponseVo vo,
            @AuthenticationPrincipal CustomUserDetails user
    ) {
        // memberUuid 추출
        redisService.addRecentViewHouse(user.getUserUuid(), RecentViewHouseResponseDto.from(vo));
        return BaseResponse.ok();
    }
    @Operation(summary = "최근 본 아파트 조회", description = "회원의 최근 본 아파트 조회")
    @GetMapping("/recent-view-house")
    public BaseResponse<List<RecentViewHouseResponseVo>> getRecentViewHouseList(
            @AuthenticationPrincipal CustomUserDetails user
    ) {
        List<RecentViewHouseResponseDto> res = redisService.getRecentViewHouseList(user.getUserUuid());
        return BaseResponse.of(
                res!=null?res.stream().map(RecentViewHouseResponseDto::from).toList() : null
        );
    }
}
