package com.ssafy.home.Heo.house.controller;

import com.ssafy.home.Heo.common.base.BaseResponse;
import com.ssafy.home.Heo.common.page.PageRequestDto;
import com.ssafy.home.Heo.common.page.PageResponseDto;
import com.ssafy.home.Heo.deal.dto.out.DealInfoResponseDto;
import com.ssafy.home.Heo.deal.vo.DealInfoResponseVo;
import com.ssafy.home.Heo.house.condition.SearchCondition;
import com.ssafy.home.Heo.house.dto.out.HouseDetailResponseDto;
import com.ssafy.home.Heo.house.dto.out.HouseMarkerResponseDto;
import com.ssafy.home.Heo.house.dto.out.HouseResponseDto;
import com.ssafy.home.Heo.house.vo.out.HouseMarkerResponseVo;
import com.ssafy.home.Heo.house.vo.out.HouseResponseVo;
import com.ssafy.home.Heo.house.service.HouseService;
import com.ssafy.home.Heo.house.vo.out.HouseDetailResponseVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/api/v1/houses")
public class HouseController {
    private final HouseService service;

    @Operation(summary = "집 정보 조회", description = "아파트시퀀스(apt_seq) 로 매물 상세 조회", tags = {"house"})
    @GetMapping("/{aptSeq}")
    public BaseResponse<HouseDetailResponseVo> findHouseByAptSeq(
            @Parameter(
                    description = "아파트 코드",
                    example = "11110-100"
            )
            @PathVariable(name = "aptSeq")String aptSeq)
    throws SQLException {
        return  BaseResponse.of(
                HouseDetailResponseDto.from(service.findHouseByAptSeq(aptSeq))
        );
    }

    /**
     *  거래내역 리스트 필터링[시군구코드, 읍면동코드]  , 정렬조건[최신순,오래된순,높은가격,낮은가격]
     */
    @Operation(summary = "아파트 리스트 조회 [필터링, 정렬] 페이지네이션 ",
            description = "필터링[시군구코드+읍면동코드] 정렬[준공년도, 북마크(미구현) ]", tags = {"house"})
    @GetMapping("/condition-list")
    public  PageResponseDto<HouseResponseVo> findHousesByCondition(
            @ParameterObject SearchCondition searchCondition) throws SQLException {
        PageResponseDto<HouseResponseDto> dtoPage = service.findHousesByCondition(searchCondition);
        List<HouseResponseVo> voList = dtoPage.getDtoList()
                .stream()
                .map(HouseResponseDto::from) // dto → vo
                .collect(Collectors.toList());
        // 리스트만 VO 리스트로 교체
        PageResponseDto<HouseResponseVo> res =
                (PageResponseDto<HouseResponseVo>) (PageResponseDto<?>) dtoPage; // 제네릭 타입을 제거한 후  VO 타입으로 강제 형변환
        res.setDtoList(voList);
        return res;
    }


    @Operation(summary = "동의 아파트 리스트 조회",
            description = "해당 동의 모든 아파트 정보 리턴", tags = {"house"})
    @GetMapping("/dong/apt-list")
    public BaseResponse<List<HouseMarkerResponseVo>> findAllHousesByDong(
            @Parameter(description = "시군구 코드", example = "11110")
            @RequestParam String sggCd,

            @Parameter(description = "읍면동 코드", example = "17500")
            @RequestParam String umdCd
    ) throws SQLException {
        // Service → Dto 리스트 반환
        List<HouseMarkerResponseDto> dtoList = service.findAllHousesByDong(sggCd, umdCd);
        return BaseResponse.of(
                dtoList.stream()
                        .map(HouseMarkerResponseDto::from)
                        .collect(Collectors.toList())
        );
    }
    @Operation(summary = "좌표(위도경도)로 아파트 리스트 조회",
            description = "해당 좌표 범위에 든 아파트 정보 리턴", tags = {"house"})
    @GetMapping("/around")
    public BaseResponse<List<HouseMarkerResponseVo>> findHousesByLatLngRange(
            @Parameter(description = "위도 최소값", example = "37.5207")
            @RequestParam double minLat,
            @Parameter(description = "위도 최대값", example = "37.5483")
            @RequestParam double maxLat,
            @Parameter(description = "경도 최소값", example = "126.9536")
            @RequestParam double minLng,
            @Parameter(description = "경도 최대값", example = "127.0099")
            @RequestParam double maxLng) {

        List<HouseMarkerResponseDto> dtoList = service.findHousesByLatLngRange(minLat, maxLat, minLng, maxLng);

        return BaseResponse.of(
                dtoList.stream()
                        .map(HouseMarkerResponseDto::from)
                        .collect(Collectors.toList())
        );
    }

}
