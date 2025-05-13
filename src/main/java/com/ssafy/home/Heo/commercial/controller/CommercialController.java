package com.ssafy.home.Heo.commercial.controller;

import com.ssafy.home.Heo.bookmark.dto.out.BookmarkResponseDto;
import com.ssafy.home.Heo.commercial.service.CommercialService;
import com.ssafy.home.Heo.commercial.vo.in.CommercialRequestRadiusVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/api/Commercial")
public class CommercialController {

    private final CommercialService service;

    @Operation(summary = "즐겨찾기 조회", description = "즐겨찾기 조회", tags = {"즐겨찾기"})
    @GetMapping("/list/")
    public ResponseEntity<?> list( @ParameterObject CommercialRequestRadiusVo vo) throws SQLException {
        List<CommercialRequestRadiusVo> list = service.getCommercialInRadius(vo.from(vo));
        return ResponseEntity.ok(list);
    }

//    @GetMapping("/radius")
//    public ResponseEntity<String> getStoreListInRadius(
//            @RequestParam double cx,
//            @RequestParam double cy,
//            @RequestParam(defaultValue = "1000") int radius
//    ) {
//        String result = storeService.getStoresInRadius(cx, cy, radius);
//        return ResponseEntity.ok(result);
//    }
}


