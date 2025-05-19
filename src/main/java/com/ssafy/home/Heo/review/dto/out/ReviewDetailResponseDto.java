package com.ssafy.home.Heo.review.dto.out;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ssafy.home.Heo.board.vo.out.BoardDetailResponseVo;
import com.ssafy.home.Heo.review.vo.out.ReviewDetailResponseVo;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReviewDetailResponseDto {
    private int reviewId;                            // 공지사항 코드
    private String memberUuid;                       // 사용자 uuid
    private String memberName;                       // 사용자 이름
    private String content;                          // 내용
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime createdAt;                 // 생성일
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime updatedAt;                 // 마지막수정

    public static ReviewDetailResponseVo from (ReviewDetailResponseDto dto){
        return ReviewDetailResponseVo.builder()
                .reviewId(dto.getReviewId())
                .content(dto.getContent())
                .memberUuid(dto.getMemberUuid())
                .memberName(dto.getMemberName())
                .createdAt(dto.getCreatedAt())
                .updatedAt(dto.getUpdatedAt())
                .build();
    }
}
