package com.ssafy.home.Heo.bookmark.dto.in;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ssafy.home.Heo.bookmark.entity.BookmarkEntity;
import com.ssafy.home.Heo.bookmark.vo.in.BookmarkSaveVo;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookmarkSaveDto {
    @Schema(description = "즐겨찾기 ID", example = "")
    private int bookmarkId;                            // 즐겨찾기 코드
    @Schema(description = "즐겨찾기 사용자 ID", example = "63f912c8-2b04-11f0-a5b7-0242ac110002")
    private String memberUuid;                              // 사용자 코드
    @Schema(description = "즐겨찾기 사용자 이름", example = "")
    private String memberName;                         // 사용자 이름
    @Schema(description = "즐겨찾기 아파트 ID", example = "11110-100")
    private String aptSeq;                             // 아파트 코드
    @Schema(description = "즐겨찾기 아파트 이름", example = "")
    private String aptName;                            // 아파트 이름
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime createdAt;                // 생성일
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime updatedAt;                // 마지막수정

    public static BookmarkEntity from (BookmarkSaveDto bookmarksavedto){
        return BookmarkEntity.builder()
                .bookmarkId(bookmarksavedto.getBookmarkId())
                .memberUuid(bookmarksavedto.getMemberUuid())
                .memberName(bookmarksavedto.getMemberName())
                .aptSeq(bookmarksavedto.getAptSeq())
                .aptName(bookmarksavedto.getAptName())
                .createdAt(bookmarksavedto.getCreatedAt())
                .updatedAt(bookmarksavedto.getUpdatedAt())
                .build();
    }


}
