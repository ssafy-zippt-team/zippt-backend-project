package com.ssafy.home.Heo.review.vo.in;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ssafy.home.Heo.board.dto.in.BoardSaveDto;
import com.ssafy.home.Heo.review.dto.in.ReviewSaveDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class ReviewSaveVo {
    @Schema(description = "사용자 UUID", example = "63f912c8-2b04-11f0-a5b7-0242ac110002")
    private String memberUuid;                           // 사용자코드
    @Schema(description = "아파트내부코드", example = "11110-100")
    private String aptSeq;                          // 아파트 내부코드
    @Schema(description = "공지사항 내용", example = "공지사항 내용 저장완료")
    private String content;                         // 내용
    @Schema(description = "생성일(공백)", example = "")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime createdAt;                // 생성일
    @Schema(description = "수정일(공백)", example = "")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime updatedAt;                // 마지막수정

    public static ReviewSaveDto from (ReviewSaveVo vo){
        return ReviewSaveDto.builder()
                .memberUuid(vo.getMemberUuid())
                .aptSeq(vo.getAptSeq())
                .content(vo.getContent())
                .createdAt(vo.getCreatedAt())
                .updatedAt(vo.getUpdatedAt())
                .build();
    }


}
