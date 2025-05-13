package com.ssafy.home.Heo.address.controller;

import com.ssafy.home.Heo.address.dto.out.AddressResponseDto;
import com.ssafy.home.Heo.address.service.AddressService;
import com.ssafy.home.Heo.address.vo.out.AddressResponseVo;
import com.ssafy.home.Heo.common.base.BaseResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/api/v1/addresses")
public class AdressController {
    private final AddressService service;

    /*==============================================================
        시 조회
    ==============================================================*/
    @Operation(summary = "시 조회", description = "시 조회", tags = {"주소"})
    @GetMapping("/getCitylist")
    public BaseResponse<List<AddressResponseVo>> getCitylist() throws SQLException {
        List<AddressResponseVo> list = service.getCityList().stream()
                .map(AddressResponseDto::from) // 변환
                .collect(Collectors.toList());
        return BaseResponse.of(list);
    }
    /*==============================================================
        시 조회 END
    ==============================================================*/
    /*==============================================================
        구 조회
    ==============================================================*/
    @Operation(summary = "구 목록 조회", description = "구 목록 조회", tags = {"주소"})
    @GetMapping("/getGulist/{citySeq}")
    public BaseResponse<List<AddressResponseVo>> getGulist(
            @Parameter(description = "시 내부코드", example = "11")
            @PathVariable(name = "citySeq") String citySeq) throws SQLException {
        List<AddressResponseVo> list = service.getGuList(citySeq).stream()
                .map(AddressResponseDto::from) // 변환
                .collect(Collectors.toList());
        return BaseResponse.of(list);
    }
    /*==============================================================
        구 조회 END
    ==============================================================*/
    /*==============================================================
        동 조회
    ==============================================================*/
    @Operation(summary = "동 목록 조회", description = "동 목록 조회", tags = {"주소"})
    @GetMapping("/getDonglist/{citySeq}/{guSeq}")
    public BaseResponse<List<AddressResponseVo>> getDonglist(
            @Parameter(description = "시 내부코드", example = "11") @PathVariable(name = "citySeq") String citySeq,
            @Parameter(description = "구 내부코드", example = "110") @PathVariable(name = "guSeq") String guSeq
                                                            ) throws SQLException {
        List<AddressResponseVo> list = service.getDongList(citySeq, guSeq).stream()
                .map(AddressResponseDto::from) // 변환
                .collect(Collectors.toList());
        return BaseResponse.of(list);
    }
    /*==============================================================
        동 조회 END
    ==============================================================*/


}
