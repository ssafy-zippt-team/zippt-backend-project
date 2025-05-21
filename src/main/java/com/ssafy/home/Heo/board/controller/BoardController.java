package com.ssafy.home.Heo.board.controller;

import com.ssafy.home.Heo.board.dto.out.BoardDetailResponseDto;
import com.ssafy.home.Heo.board.dto.out.BoardResponseDto;
import com.ssafy.home.Heo.board.entity.BoardEntity;
import com.ssafy.home.Heo.board.service.BoardService;
import com.ssafy.home.Heo.board.vo.in.BoardSaveVo;
import com.ssafy.home.Heo.board.vo.in.BoardUpdateVo;
import com.ssafy.home.Heo.board.vo.out.BoardDetailResponseVo;
import com.ssafy.home.Heo.board.vo.out.BoardResponseVo;
import com.ssafy.home.Heo.common.base.BaseResponse;
import com.ssafy.home.Heo.common.page.PageRequestDto;
import com.ssafy.home.Heo.common.page.PageResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@RestController
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/api/v1/boards")
public class BoardController {
    private final BoardService service;
    /*==============================================================
        공지사항 전체 조회
    ==============================================================*/
    @Operation(summary = "공지사항 조회", description = "공지사항 전체 조회", tags = {"공지사항"})
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
    @Operation(summary = "공지사항 디테일 조회", description = "공지사항 내부코드로 조회", tags = {"공지사항"})
    @GetMapping("/{boardId}")
    public BaseResponse<BoardDetailResponseVo> detaillist(
            @Parameter(description = "공지사항 ID", example = "1")
            @PathVariable(name = "boardId")String boardId) throws SQLException {
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
    @Operation(summary = "공지사항 저장", description = "공지사항 저장", tags = {"공지사항"})
    @PostMapping("/save")
    public BaseResponse<String> insert(@ParameterObject BoardSaveVo boardsavevo) throws SQLException {
        System.out.println("Insert 객체 받아오기 boardsavevo = " + boardsavevo);
        int cnt = service.insert(BoardSaveVo.from(boardsavevo));
        if (cnt > 0) {
            return BaseResponse.of("공지사항이 성공적으로 저장되었습니다.");
        } else {
            return BaseResponse.of("공지사항 저장에 실패했습니다.");
        }
    }
    /*==============================================================
        공지사항 저장 END
    ==============================================================*/
    /*==============================================================
        공지사항 삭제
    ==============================================================*/
    @Operation(summary = "공지사항 삭제", description = "공지사항 삭제", tags = {"공지사항"})
    @DeleteMapping("/{boardId}")
    public BaseResponse<String> delete(
            @Parameter(description = "공지사항 ID", example = "1")
            @PathVariable(name = "boardId")String boardId) throws SQLException {
        System.out.println("boardId = " + boardId);
        service.delete(boardId);
        return BaseResponse.of("공지사항이 성공적으로 삭제되었습니다.");
    }
    /*==============================================================
        공지사항 삭제 END
    ==============================================================*/
    /*==============================================================
        공지사항 수정
    ==============================================================*/
    @Operation(summary = "공지사항 수정", description = "공지사항 수정", tags = {"공지사항"})
    @PatchMapping("/update")
    public BaseResponse<String> update(@ParameterObject BoardUpdateVo boardupdatevo) throws SQLException {
        System.out.println("객체 받아오기 boardupdatevo = " + boardupdatevo);
        service.update(BoardUpdateVo.from(boardupdatevo));
        return BaseResponse.of("공지사항이 성공적으로 수정되었습니다.");
    }
    /*==============================================================
        공지사항 수정 END
    ==============================================================*/

}
