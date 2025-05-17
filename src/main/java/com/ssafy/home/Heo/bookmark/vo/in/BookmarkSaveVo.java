package com.ssafy.home.Heo.bookmark.vo.in;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ssafy.home.Heo.board.dto.in.BoardSaveDto;
import com.ssafy.home.Heo.board.vo.in.BoardSaveVo;
import com.ssafy.home.Heo.bookmark.dto.in.BookmarkSaveDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class BookmarkSaveVo {
    @Schema(description = "즐겨찾기 ID(공백)", example = "0")
    private int bookmarkId;                            // 즐겨찾기 코드
    @Schema(description = "사용자 uuiD", example = "63f912c8-2b04-11f0-a5b7-0242ac110002")
    private String memberUuid;                              // 사용자 코드
    @Schema(description = "사용자 이름(공백)", example = "")
    private String memberName;                         // 사용자 이름
    @Schema(description = "아파트 내부코드", example = "11110-100")
    private String aptSeq;                             // 아파트 코드
    @Schema(description = "아파트 이름(공백)", example = "")
    private String aptName;                            // 아파트 이름
    @Schema(description = "생성일(공백)", example = "")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime createdAt;                   // 생성일
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    @Schema(description = "수정일(공백)", example = "")
    private LocalDateTime updatedAt;                   // 마지막수정

    public static BookmarkSaveDto from (BookmarkSaveVo bookmarksavevo){
        return BookmarkSaveDto.builder()
                .bookmarkId(bookmarksavevo.getBookmarkId())
                .memberUuid(bookmarksavevo.getMemberUuid())
                .memberName(bookmarksavevo.getMemberName())
                .aptSeq(bookmarksavevo.getAptSeq())
                .aptName(bookmarksavevo.getAptName())
                .createdAt(bookmarksavevo.getCreatedAt())
                .updatedAt(bookmarksavevo.getUpdatedAt())
                .build();
    }
}
