package com.ssafy.home.Heo.board.vo.in;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ssafy.home.Heo.board.dto.in.BoardSaveDto;
import com.ssafy.home.Heo.board.dto.in.BoardUpdateDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class BoardUpdateVo {

    @Schema(description = "공지사항 ID", example = "1")
    private int boardId;                            // 공지사항 코드
    @Schema(description = "공지사항 제목", example = "공지사항 제목 수정완료")
    private String title;                           // 제목
    @Schema(description = "공지사항 내용", example = "공지사항 내용 수정완료")
    private String content;                         // 내용
    @Schema(description = "사용자 ID(공백)", example = "")
    private String memberId;                           // 사용자코드
    @Schema(description = "사용자 이름(공백)", example = "")
    private String memberName;                      // 사용자 이름
    @Schema(description = "생성일(공백)", example = "")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime createdAt;                // 생성일
    @Schema(description = "수정일(공백)", example = "")
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
