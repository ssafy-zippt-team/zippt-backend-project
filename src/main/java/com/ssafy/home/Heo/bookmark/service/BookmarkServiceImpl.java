package com.ssafy.home.Heo.bookmark.service;

import com.ssafy.home.Heo.bookmark.dto.in.BookmarkSaveDto;
import com.ssafy.home.Heo.bookmark.dto.out.BookmarkResponseDto;
import com.ssafy.home.Heo.bookmark.entity.BookmarkEntity;
import com.ssafy.home.Heo.bookmark.repository.BookmarkDao;
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
    public List<BookmarkResponseDto> getBookmarkList(String memberUuid) throws SQLException {
        return dao.getBookmarkList(memberUuid);
    }
    /*==============================================================
      즐겨찾기 조회 END
    ==============================================================*/
    /*==============================================================
       즐겨찾기 횟수 조회
    ==============================================================*/
    @Override
    public Integer getBookmarkCnt(String aptSeq) throws SQLException {
        return dao.getBookmarkCnt(aptSeq);
    }
    /*==============================================================
      즐겨찾기 횟수 조회 END
    ==============================================================*/
    /*==============================================================
      즐겨찾기 등록
    ==============================================================*/
    @Override
    public void insert(BookmarkSaveDto bookmarkSaveDto) throws SQLException {
        dao.insert(BookmarkSaveDto.from(bookmarkSaveDto));
    }
    /*==============================================================
       즐겨찾기 등록 END
     ==============================================================*/
    /*==============================================================
        즐겨찾기 삭제
    ==============================================================*/
    @Override
    public void delete(String bookmarkId) throws SQLException {
        dao.delete(bookmarkId);
    }

    @Override
    public boolean checkBoolMark(String aptSeq, String memberUuid) throws SQLException {
        return dao.checkBoolMark( aptSeq, memberUuid);
    }
    /*==============================================================
        즐겨찾기 삭제 END
    ==============================================================*/
    /*==============================================================
        즐겨찾기 토글
    ==============================================================*/
    public void toggleActiveFlag(String bookmarkId) throws SQLException {
        dao.toggleActiveFlag(bookmarkId);
    }
    /*==============================================================
        즐겨찾기 토글 END
    ==============================================================*/
}
