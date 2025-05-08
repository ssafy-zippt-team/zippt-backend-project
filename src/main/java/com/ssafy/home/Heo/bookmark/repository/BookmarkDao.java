package com.ssafy.home.Heo.bookmark.repository;

import com.ssafy.home.Heo.board.dto.out.BoardDetailResponseDto;
import com.ssafy.home.Heo.board.dto.out.BoardResponseDto;
import com.ssafy.home.Heo.board.entity.Board;
import com.ssafy.home.Heo.bookmark.dto.out.BookmarkResponseDto;
import com.ssafy.home.Heo.bookmark.entity.Bookmark;
import com.ssafy.home.Heo.common.page.PageRequestDto;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;

@Mapper
@Repository
public interface BookmarkDao {

    // 즐겨찾기 조회
    List<BookmarkResponseDto> getBookmarkList(String memberId) throws SQLException;
    // 즐겨찾기 등록
    void insert(Bookmark bookmark) throws SQLException;
    // 즐겨찾기 삭제
    void delete(String bookmarkId) throws  SQLException;

}
