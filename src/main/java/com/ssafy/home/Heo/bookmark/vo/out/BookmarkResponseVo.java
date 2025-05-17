package com.ssafy.home.Heo.bookmark.vo.out;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class BookmarkResponseVo {
    private int bookmarkId;                            // 즐겨찾기 코드
    private String memberUuid;                              // 사용자 코드
    private String memberName;                         // 사용자 이름
    private String aptSeq;                                // 아파트 코드
    private String aptName;                            // 아파트 이름
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime createdAt;                // 생성일
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime updatedAt;                // 마지막수정
}
