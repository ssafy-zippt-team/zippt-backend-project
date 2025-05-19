package com.ssafy.home.Heo.review.dto.in;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ssafy.home.Heo.board.entity.BoardEntity;
import com.ssafy.home.Heo.review.entity.ReviewEntity;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReviewUpdateDto {
    private int reviewId;                           // 리뷰 코드
    private String content;                         // 내용
    private String memberUuid;                      // 사용자코드
    private String aptSeq;                          // 아파트 내부코드
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime createdAt;                // 생성일
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime updatedAt;                // 마지막수정

    public static ReviewEntity from (ReviewUpdateDto dto){
        return ReviewEntity.builder()
                .reviewId(dto.getReviewId())
                .aptSeq(dto.getAptSeq())
                .memberUuid(dto.getMemberUuid())
                .content(dto.getContent())
                .createdAt(dto.getCreatedAt())
                .updatedAt(dto.getUpdatedAt())
                .build();
    }

}
