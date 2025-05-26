package com.ssafy.home.Heo.bookmark.service;

import com.ssafy.home.Heo.bookmark.dto.in.BookmarkSaveDto;
import com.ssafy.home.Heo.bookmark.dto.out.BookmarkResponseDto;
import com.ssafy.home.Heo.bookmark.entity.BookmarkEntity;
import com.ssafy.home.Heo.common.page.PageRequestDto;
import com.ssafy.home.Heo.common.page.PageResponseDto;

import java.sql.SQLException;
import java.util.List;

public interface BookmarkService {
    //즐겨찾기 조회
    PageResponseDto<BookmarkResponseDto> getBookmarkList(PageRequestDto pageRequestDto, String memberUuid) throws SQLException;
    //즐겨찾기 삭제(토글로 대체)
    public void delete(String bookmarkId) throws SQLException;

    //즐겨찾기 flag 토글 기능
    void toggleActiveFlag(String memberUuid,String aptSeq) throws SQLException;

    //즐겨찾기 조회
    Integer getBookmarkCnt(String aptSeq) throws SQLException;

    // 즐겨찾기 여부 확인
    public boolean checkBoolMark(String aptSeq, String memberUuid) throws SQLException;

}
