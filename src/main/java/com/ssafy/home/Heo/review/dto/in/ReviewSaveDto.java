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
public class ReviewSaveDto {
    private String memberUuid;                           // 사용자코드
    private String content;                         // 내용
    private String aptSeq;                          // 아파트 내부코드
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime createdAt;                // 생성일
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime updatedAt;                // 마지막수정

    public static ReviewEntity from (ReviewSaveDto reviewsavedto){
        return ReviewEntity.builder()
                .memberUuid(reviewsavedto.getMemberUuid())
                .aptSeq(reviewsavedto.getAptSeq())
                .content(reviewsavedto.getContent())
                .createdAt(reviewsavedto.getCreatedAt())
                .updatedAt(reviewsavedto.getUpdatedAt())
                .build();
    }

}
