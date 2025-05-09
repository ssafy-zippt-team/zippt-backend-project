package com.ssafy.home.Heo.board.vo.in;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ssafy.home.Heo.board.dto.in.BoardSaveDto;
import com.ssafy.home.Heo.board.dto.in.BoardUpdateDto;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class BoardUpdateVo {
    private int boardId;                            // 공지사항 코드
    private String title;                           // 제목
    private String content;                         // 내용
    private int memberId;                           // 사용자코드
    private String memberName;                      // 사용자 이름
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime createdAt;                // 생성일
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime updatedAt;                // 마지막수정

    public static BoardUpdateDto from (BoardUpdateVo boardupdatevo){
        return BoardUpdateDto.builder()
                .boardId(boardupdatevo.getBoardId())
                .title(boardupdatevo.getTitle())
                .content(boardupdatevo.getContent())
                .memberId(boardupdatevo.getMemberId())
                .memberName(boardupdatevo.getMemberName())
                .createdAt(boardupdatevo.getCreatedAt())
                .updatedAt(boardupdatevo.getUpdatedAt())
                .build();
    }


}
