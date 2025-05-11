package com.ssafy.home.Heo.bookmark.vo.in;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ssafy.home.Heo.board.dto.in.BoardSaveDto;
import com.ssafy.home.Heo.board.vo.in.BoardSaveVo;
import com.ssafy.home.Heo.bookmark.dto.in.BookmarkSaveDto;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class BookmarkSaveVo {
    private int bookmarkId;                            // 즐겨찾기 코드
    private int memberId;                              // 사용자 코드
    private String memberName;                         // 사용자 이름
    private String aptSeq;                             // 아파트 코드
    private String aptName;                            // 아파트 이름
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime createdAt;                   // 생성일
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime updatedAt;                   // 마지막수정

    public static BookmarkSaveDto from (BookmarkSaveVo bookmarksavevo){
        return BookmarkSaveDto.builder()
                .bookmarkId(bookmarksavevo.getBookmarkId())
                .memberId(bookmarksavevo.getMemberId())
                .memberName(bookmarksavevo.getMemberName())
                .aptSeq(bookmarksavevo.getAptSeq())
                .aptName(bookmarksavevo.getAptName())
                .createdAt(bookmarksavevo.getCreatedAt())
                .updatedAt(bookmarksavevo.getUpdatedAt())
                .build();
    }
}
