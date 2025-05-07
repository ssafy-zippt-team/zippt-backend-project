package com.ssafy.home.Heo.board.controller;

import com.ssafy.home.Heo.board.dto.out.BoardDetailResponseDto;
import com.ssafy.home.Heo.board.dto.out.BoardResponseDto;
import com.ssafy.home.Heo.board.service.BoardService;
import com.ssafy.home.Heo.board.vo.out.BoardDetailResponseVo;
import com.ssafy.home.Heo.common.base.BaseResponse;
import com.ssafy.home.Heo.common.page.PageRequestDto;
import com.ssafy.home.Heo.common.page.PageResponseDto;
import com.ssafy.home.Heo.house.dto.out.HouseDetailResponseDto;
import com.ssafy.home.Heo.house.vo.out.HouseDetailResponseVo;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;

@RestController
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/api/v1/board")
public class BoardController {
    private final BoardService service;
    /*==============================================================
        공지사항 전체 조회
    ==============================================================*/
//    @Operation(summary = "테스트", description = "테스트 핸들", tags = {"board"})
    @GetMapping("/list")
    public PageResponseDto<BoardResponseDto> list(PageRequestDto pageRequestDto) throws SQLException {
        return service.getBoardList(pageRequestDto);
    }
    /*==============================================================
        공지사항 상세 조회 END
    ==============================================================*/
    /*==============================================================
        공지사항 상세 조회
    ==============================================================*/
    @Operation(summary = "공지사항 디테일 조회", description = "공지사항 내부코드로 조회", tags = {"board"})
    @GetMapping("/{boardId}")
    public BaseResponse<BoardDetailResponseVo> detaillist(@PathVariable(name = "boardId")String boardId) throws SQLException {
        return  BaseResponse.of(
                BoardDetailResponseDto.from(service.findBoardByboardId(boardId))
        );
    }
    /*==============================================================
        공지사항 전체 조회 END
    ==============================================================*/

}
