package com.ssafy.home.Heo.review.vo.out;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Builder
public class ReviewSimpleResponseVo {
    private String memberName;                       // 사용자 이름
    private String content;                          // 내용
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime updatedAt;                 // 마지막수정
}
