package com.ssafy.home.Heo.adress.controller;

import com.ssafy.home.Heo.adress.dto.out.AdressResponseDto;
import com.ssafy.home.Heo.adress.service.AdressService;
import com.ssafy.home.Heo.adress.vo.out.AdressResponseVo;
import com.ssafy.home.Heo.board.dto.out.BoardDetailResponseDto;
import com.ssafy.home.Heo.board.dto.out.BoardResponseDto;
import com.ssafy.home.Heo.board.service.BoardService;
import com.ssafy.home.Heo.board.vo.in.BoardSaveVo;
import com.ssafy.home.Heo.board.vo.in.BoardUpdateVo;
import com.ssafy.home.Heo.board.vo.out.BoardDetailResponseVo;
import com.ssafy.home.Heo.common.base.BaseResponse;
import com.ssafy.home.Heo.common.page.PageRequestDto;
import com.ssafy.home.Heo.common.page.PageResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/api/v1/boards")
public class AdressController {
    private final AdressService service;
    /*==============================================================
        시 목록 조회
    ==============================================================*/
    @Operation(summary = "시 목록 조회", description = "시 목록 조회", tags = {"adress"})
    @GetMapping("/citylist/{citySeq}")
    public BaseResponse<List<AdressResponseVo>> list(@PathVariable(name = "citySeq")String cityseq ) throws SQLException {
        return BaseResponse.of (service.getCityList(cityseq).stream()
                .map(AdressResponseDto::from) // 변환
                .collect(Collectors.toList()));
    }
    /*==============================================================
        시 목록 조회 END
    ==============================================================*/



}
