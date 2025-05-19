package com.ssafy.home.Heo.review.vo.out;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class ReviewDetailResponseVo {
    private int reviewId;                            // 리뷰 코드
    private String memberUuid;                       // 사용자 uuid
    private String memberName;                       // 사용자 이름
    private String content;                          // 내용
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime createdAt;                 // 생성일
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime updatedAt;                 // 마지막수정
}
