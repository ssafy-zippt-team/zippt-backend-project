package com.ssafy.home.Heo.house.controller;

import com.ssafy.home.Heo.common.base.BaseResponse;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/api/v1/house")

public class TestController {
    @Operation(summary = "테스트", description = "테스트 핸들", tags = {"house"})
    @GetMapping("/")
    public BaseResponse<Void> test(){
        return new BaseResponse(
            "SUCCESS"
        );
    }

//    @Operation(summary = "테스트", description = "테스트 핸들", tags = {"test"})
//    @GetMapping("/get-test")
//    public String test(){
//
//        return "SUCCESS";
//
//    }
}
