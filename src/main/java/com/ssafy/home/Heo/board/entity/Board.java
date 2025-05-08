package com.ssafy.home.Heo.board.entity;

import lombok.*;
import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonFormat;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Board {
    private int boardId;                            // 공지사항 코드
    private String title;                           // 제목
    private String content;                         // 내용
    private int memberId;                           // 사용자코드
    private String memberName;                      // 사용자 이름
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime createdAt;                // 생성일
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime updatedAt;                // 마지막수정
}
