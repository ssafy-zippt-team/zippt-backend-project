package com.ssafy.home.Heo.board.repository;

import com.ssafy.home.Heo.board.dto.in.BoardSaveDto;
import com.ssafy.home.Heo.board.dto.out.BoardDetailResponseDto;
import com.ssafy.home.Heo.board.dto.out.BoardResponseDto;
import com.ssafy.home.Heo.board.entity.BoardEntity;
import com.ssafy.home.Heo.common.page.PageRequestDto;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;

@Mapper
@Repository
public interface BoardDao {

    // 공지 사항 조회
    List<BoardResponseDto> getBoardList(PageRequestDto pageRequestDto) throws SQLException;
    // 공지 사항 조회 cnt 가져오기
    int getBoardListCount() throws SQLException;
    // 공지 사항 상세조회
    BoardDetailResponseDto findBoardByboardId(String boardId) throws SQLException;
    // 공지 사항 등록
    int insert(BoardEntity boardentity) throws SQLException;
    // 공지 사항 삭제
    void delete(String boardId) throws  SQLException;
    // 공지 사항 업데이트
    void update(BoardEntity boardentity) throws SQLException;


}
