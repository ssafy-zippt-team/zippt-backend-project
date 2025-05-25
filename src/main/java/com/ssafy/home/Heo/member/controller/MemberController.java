package com.ssafy.home.Heo.member.controller;

import com.ssafy.home.Heo.bookmark.dto.out.BookmarkResponseDto;
import com.ssafy.home.Heo.bookmark.service.BookmarkService;
import com.ssafy.home.Heo.common.base.BaseResponse;
import com.ssafy.home.Heo.member.dto.in.MemberRequestDto;
import com.ssafy.home.Heo.member.dto.out.MemberResponseDto;
import com.ssafy.home.Heo.member.service.MemberService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/api/v1/members")
public class MemberController {
    private final MemberService service;
    /*==============================================================
       mypage  조회
    ==============================================================*/
    @Operation(summary = "mypage 조회", description = "mypage 조회", tags = {"마이페이지"})
    @GetMapping("/mypage/{memberUuid}")
    public BaseResponse<MemberResponseDto> list(
            @Parameter(description = "memberUuid", example = "63f912c8-2b04-11f0-a5b7-0242ac110002")
            @PathVariable(name = "memberUuid")String memberuuid) throws SQLException {
        System.out.println("memberuuid = " + memberuuid);
        return BaseResponse.of(service.getMember(memberuuid));
    }
    /*==============================================================
       mypage 조회 END
    ==============================================================*/
     /*==============================================================
       mypage 수정
    ==============================================================*/
    @Operation(summary = "mypage 정보수정", description = "mypage 정보수정", tags = {"마이페이지"})
    @GetMapping("/memberupdate")
    public BaseResponse<String> memberupdate(
            @ParameterObject MemberRequestDto requestDto) throws SQLException {
        service.memberUpdate(requestDto);

        return BaseResponse.of("수정에 성공하였습니다.");
    }
    /*==============================================================
        mypage 수정 END
    ==============================================================*/



}
