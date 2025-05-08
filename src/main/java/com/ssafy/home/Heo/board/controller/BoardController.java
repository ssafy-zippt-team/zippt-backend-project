package com.ssafy.home.Heo.board.controller;

import com.ssafy.home.Heo.board.dto.out.BoardDetailResponseDto;
import com.ssafy.home.Heo.board.dto.out.BoardResponseDto;
import com.ssafy.home.Heo.board.entity.Board;
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
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    /*==============================================================
        공지사항 저장
    ==============================================================*/
    @Operation(summary = "공지사항 저장", description = "공지사항 저장", tags = {"board"})
    @PostMapping("/save")
    public ResponseEntity<Void> insert(@ParameterObject Board board) throws SQLException {
        System.out.println("Insert 객체 받아오기 board = " + board);
        int cnt = service.insert(board);
        return ResponseEntity.ok().build();
    }
    /*==============================================================
        공지사항 저장 END
    ==============================================================*/
    /*==============================================================
        공지사항 삭제
    ==============================================================*/
    @Operation(summary = "공지사항 삭제", description = "공지사항 삭제", tags = {"board"})
    @PostMapping("/delete/{boardId}")
    public ResponseEntity<Void> delete(@PathVariable(name = "boardId")String boardId) throws SQLException {
        System.out.println("boardId = " + boardId);
        service.delete(boardId);
        return ResponseEntity.ok().build();
    }
    /*==============================================================
        공지사항 삭제 END
    ==============================================================*/
    /*==============================================================
        공지사항 수정
    ==============================================================*/
    @Operation(summary = "공지사항 수정", description = "공지사항 수정", tags = {"board"})
    @PostMapping("/update")
    public ResponseEntity<Void> update(@ParameterObject Board board) throws SQLException {
        System.out.println("객체 받아오기 board = " + board);
        service.update(board);
        return ResponseEntity.ok().build();
    }
    /*==============================================================
        공지사항 수정 END
    ==============================================================*/

}
