package com.ssafy.home.Heo.bookmark.dto.in;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ssafy.home.Heo.bookmark.entity.BookmarkEntity;
import com.ssafy.home.Heo.bookmark.vo.in.BookmarkSaveVo;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookmarkSaveDto {
    private int bookmarkId;                            // 즐겨찾기 코드
    private int memberId;                              // 사용자 코드
    private String memberName;                         // 사용자 이름
    private String aptSeq;                             // 아파트 코드
    private String aptName;                            // 아파트 이름
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime createdAt;                // 생성일
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime updatedAt;                // 마지막수정

    public static BookmarkEntity from (BookmarkSaveDto bookmarksavedto){
        return BookmarkEntity.builder()
                .bookmarkId(bookmarksavedto.getBookmarkId())
                .memberId(bookmarksavedto.getMemberId())
                .memberName(bookmarksavedto.getMemberName())
                .aptSeq(bookmarksavedto.getAptSeq())
                .aptName(bookmarksavedto.getAptName())
                .createdAt(bookmarksavedto.getCreatedAt())
                .updatedAt(bookmarksavedto.getUpdatedAt())
                .build();
    }


}
