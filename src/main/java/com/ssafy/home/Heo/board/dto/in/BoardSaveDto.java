package com.ssafy.home.Heo.board.dto.in;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ssafy.home.Heo.board.entity.BoardEntity;
import com.ssafy.home.Heo.board.vo.in.BoardSaveVo;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BoardSaveDto {
    private int boardId;                            // 공지사항 코드
    private String title;                           // 제목
    private String content;                         // 내용
    private int memberId;                           // 사용자코드
    private String memberName;                      // 사용자 이름
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime createdAt;                // 생성일
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime updatedAt;                // 마지막수정

    public static BoardEntity from (BoardSaveDto boardsavedto){
        return BoardEntity.builder()
                .boardId(boardsavedto.getBoardId())
                .title(boardsavedto.getTitle())
                .content(boardsavedto.getContent())
                .memberId(boardsavedto.getMemberId())
                .memberName(boardsavedto.getMemberName())
                .createdAt(boardsavedto.getCreatedAt())
                .updatedAt(boardsavedto.getUpdatedAt())
                .build();
    }

}
