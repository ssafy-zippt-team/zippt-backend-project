package com.ssafy.home.Heo.bookmark.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookmarkEntity {
    private int bookmarkId;                            // 즐겨찾기 코드
    private String memberUuid;                              // 사용자 코드
    private String memberName;                         // 사용자 이름
    private String aptSeq;                                // 아파트 코드
    private String aptName;                            // 아파트 이름
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime createdAt;                // 생성일
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime updatedAt;                // 마지막수정
    private String activeFlag;                      // 플래그
}
