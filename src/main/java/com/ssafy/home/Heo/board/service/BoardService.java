package com.ssafy.home.Heo.board.service;

import com.ssafy.home.Heo.board.dto.out.BoardDetailResponseDto;
import com.ssafy.home.Heo.board.dto.out.BoardResponseDto;
import com.ssafy.home.Heo.board.entity.Board;
import com.ssafy.home.Heo.common.page.PageRequestDto;
import com.ssafy.home.Heo.common.page.PageResponseDto;
import com.ssafy.home.Heo.house.dto.out.HouseDetailResponseDto;

import java.sql.SQLException;

public interface BoardService {
    //공지사항 전체 조회
    PageResponseDto<BoardResponseDto> getBoardList(PageRequestDto pageRequestDto) throws SQLException;

    //상세 조회
    BoardDetailResponseDto findBoardByboardId(String boardId) throws SQLException;

    //공지사항 등록
    public int insert(Board board) throws SQLException;
    //공지사항 삭제
    public void delete(String boardId) throws SQLException;
    //공지사항 업데이트
    public void update(Board board) throws SQLException;
}
