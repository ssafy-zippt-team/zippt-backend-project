package com.ssafy.home.Heo.bookmark.repository;

import com.ssafy.home.Heo.bookmark.dto.out.BookmarkResponseDto;
import com.ssafy.home.Heo.bookmark.entity.BookmarkEntity;
import com.ssafy.home.Heo.common.page.PageRequestDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;

@Mapper
@Repository
public interface BookmarkDao {

    // 즐겨찾기 조회
    List<BookmarkResponseDto> getBookmarkList(String memberUuid) throws SQLException;
    // 즐겨찾기 등록
    void insert(@Param("memberUuid") String memberUuid,
                @Param("aptSeq") String aptSeq) throws SQLException;
    // 즐겨찾기 삭제(flag 도입으로 사용 안함)
    void delete(String bookmarkId) throws  SQLException;
    // 즐겨찾기 토글 기능
    void toggleActiveFlag(
            @Param("memberUuid") String memberUuid,
            @Param("aptSeq") String aptSeq) throws SQLException;
    // 토글 기능 전 테이블 체크
    Integer isExistsReview( @Param("memberUuid") String memberUuid,
                            @Param("aptSeq") String aptSeq) throws SQLException;

    // 즐겨찾기 횟수
    Integer getBookmarkCnt(String aptSeq) throws SQLException;

    // 즐겨찾기 여부 확인
    boolean checkBoolMark(String aptSeq, String memberUuid) throws SQLException;

}
