package com.ssafy.home.Heo.commercial.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ssafy.home.Heo.bookmark.dto.out.BookmarkResponseDto;
import com.ssafy.home.Heo.commercial.dto.out.CommercialResponseRadiusDto;
import com.ssafy.home.Heo.commercial.service.CommercialService;
import com.ssafy.home.Heo.commercial.vo.in.CommercialRequestRadiusVo;
import com.ssafy.home.Heo.commercial.vo.out.CommercialResponseRadiusVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.sql.SQLException;
import java.util.List;

@RestController
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/api/Commercials")
public class CommercialController {

    private final CommercialService service;

    @Operation(summary = "반경 상권 조회", description = "반경 상권 조회", tags = {"상권"})
    @GetMapping("/radius")
    public ResponseEntity<?> radius( @ParameterObject  CommercialRequestRadiusVo vo) throws SQLException, UnsupportedEncodingException, URISyntaxException, JsonProcessingException {
        System.out.println("vo = " + vo);
        List<CommercialResponseRadiusDto> list = service.getCommercialInRadius(vo.from(vo));
        System.out.println("list = " + list);
        return ResponseEntity.ok(list);
//        return null;
    }

    @Operation(summary = "반경 상권 조회", description = "반경 상권 조회", tags = {"상권"})
    @GetMapping("/{}")
    public ResponseEntity<?> radius( @ParameterObject  CommercialRequestRadiusVo vo) throws SQLException, UnsupportedEncodingException, URISyntaxException, JsonProcessingException {
        System.out.println("vo = " + vo);
        List<CommercialResponseRadiusDto> list = service.getCommercialInRadius(vo.from(vo));
        System.out.println("list = " + list);
        return ResponseEntity.ok(list);
//        return null;
    }


}


