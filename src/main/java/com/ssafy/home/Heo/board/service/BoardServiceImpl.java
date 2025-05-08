package com.ssafy.home.Heo.board.service;

import com.ssafy.home.Heo.board.dto.out.BoardDetailResponseDto;
import com.ssafy.home.Heo.board.dto.out.BoardResponseDto;
import com.ssafy.home.Heo.board.entity.Board;
import com.ssafy.home.Heo.board.repository.BoardDao;
import com.ssafy.home.Heo.common.base.BaseResponseStatus;
import com.ssafy.home.Heo.common.exception.BaseException;
import com.ssafy.home.Heo.common.page.PageRequestDto;
import com.ssafy.home.Heo.common.page.PageResponseDto;
import com.ssafy.home.Heo.house.dto.out.HouseDetailResponseDto;
import com.ssafy.home.Heo.house.dto.out.HouseResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class BoardServiceImpl implements BoardService {
    private final BoardDao dao;
    /*==============================================================
        공지사항 전체 조회
    ==============================================================*/
    @Override
    public PageResponseDto<BoardResponseDto> getBoardList(PageRequestDto pageRequestDto) throws SQLException {
        // 1. 목록 조회
        List<BoardResponseDto> list = dao.getBoardList(pageRequestDto);

        // 2. 전체 개수 조회
        int totalCount = dao.getBoardListCount();
        log.info("total = "+totalCount);
        System.out.println("totalCount = " + totalCount);
        // 3. 응답 조립
        return PageResponseDto.<BoardResponseDto> withAll()
                .dtoList(list)
                .totalCount(totalCount)
                .pageRequestDTO(pageRequestDto)
                .build();
    }
    /*==============================================================
      공지사항 전체 조회 END
    ==============================================================*/
    /*==============================================================
      공지사항 상세 조회
    ==============================================================*/
    @Override
    public BoardDetailResponseDto findBoardByboardId(String boardId) throws SQLException {
        BoardDetailResponseDto res = dao.findBoardByboardId(boardId);
        if(res == null) throw new BaseException(BaseResponseStatus.NO_EXIST_BOARD);
        return res;
    }
    /*==============================================================
        공지사항 상세 조회 END
    ==============================================================*/
    /*==============================================================
        공지사항 등록
    ==============================================================*/
    @Override
    public int insert(Board board) throws SQLException {
        int cnt = dao.insert(board);
        System.out.println("조회된 행 = " + cnt);
//        일단 보류
//        if (cnt == 0) throw new BaseException(BaseResponseStatus.NO_EXIST_BOARD);
        return cnt;
    }
    /*==============================================================
        공지사항 등록 END
    ==============================================================*/
     /*==============================================================
        공지사항 삭제
    ==============================================================*/
    @Override
    public void delete(String boardId) throws SQLException {
        dao.delete(boardId);
    }
    /*==============================================================
        공지사항 삭제 END
    ==============================================================*/
     /*==============================================================
        공지사항 업데이트
    ==============================================================*/
    @Override
    public void update(Board board) throws SQLException {
        dao.update(board);
    }
    /*==============================================================
        공지사항 업데이트 END
    ==============================================================*/
}
