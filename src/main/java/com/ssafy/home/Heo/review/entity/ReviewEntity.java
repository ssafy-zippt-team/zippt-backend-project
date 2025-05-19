package com.ssafy.home.Heo.review.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReviewEntity {
    private int reviewId;                            // 리뷰 코드
    private String memberUuid;                       // 사용자 uuid
    private String memberName;                       // 사용자 이름
    private String content;                          // 내용
    private String aptSeq;                          // 아파트 내부코드
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime createdAt;                 // 생성일
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime updatedAt;                 // 마지막수정
}
