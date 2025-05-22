package com.ssafy.home.Heo.review.dto.out;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ssafy.home.Heo.review.vo.out.ReviewSimpleResponseVo;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReviewSimpleResponseDto {
    private String memberName;                       // 사용자 이름
    private String content;                          // 내용
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime updatedAt;                 // 마지막수정

    // dto -> vo
    public static ReviewSimpleResponseVo from(ReviewSimpleResponseDto dto){
        return ReviewSimpleResponseVo.builder()
                .memberName(dto.getMemberName())
                .content(dto.getContent())
                .updatedAt(dto.updatedAt)
                .build();
    }
}
