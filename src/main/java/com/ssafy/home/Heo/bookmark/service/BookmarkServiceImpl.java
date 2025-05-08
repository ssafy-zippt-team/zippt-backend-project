package com.ssafy.home.Heo.bookmark.service;

import com.ssafy.home.Heo.board.dto.out.BoardDetailResponseDto;
import com.ssafy.home.Heo.board.dto.out.BoardResponseDto;
import com.ssafy.home.Heo.board.entity.Board;
import com.ssafy.home.Heo.board.repository.BoardDao;
import com.ssafy.home.Heo.board.service.BoardService;
import com.ssafy.home.Heo.bookmark.dto.out.BookmarkResponseDto;
import com.ssafy.home.Heo.bookmark.entity.Bookmark;
import com.ssafy.home.Heo.bookmark.repository.BookmarkDao;
import com.ssafy.home.Heo.common.base.BaseResponseStatus;
import com.ssafy.home.Heo.common.exception.BaseException;
import com.ssafy.home.Heo.common.page.PageRequestDto;
import com.ssafy.home.Heo.common.page.PageResponseDto;
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
public class BookmarkServiceImpl implements BookmarkService {
    private final BookmarkDao dao;
    /*==============================================================
       즐겨찾기 조회
    ==============================================================*/
    @Override
    public List<BookmarkResponseDto> getBookmarkList(String memberId) throws SQLException {
        return dao.getBookmarkList(memberId);
    }
    /*==============================================================
      즐겨찾기 조회 END
    ==============================================================*/
    /*==============================================================
      즐겨찾기 등록
    ==============================================================*/
    @Override
    public void insert(Bookmark bookmark) throws SQLException {
        dao.insert(bookmark);
    }
    /*==============================================================
       즐겨찾기 등록 END
     ==============================================================*/
     /*==============================================================
        즐겨찾기 삭제
    ==============================================================*/
    @Override
    public void delete(String boardId) throws SQLException {
        dao.delete(boardId);
    }
    /*==============================================================
        즐겨찾기 삭제 END
    ==============================================================*/
}
