package com.ssafy.home.Heo.board.vo.in;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ssafy.home.Heo.board.dto.in.BoardSaveDto;
import com.ssafy.home.Heo.board.dto.out.BoardDetailResponseDto;
import com.ssafy.home.Heo.board.vo.out.BoardDetailResponseVo;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class BoardSaveVo {
    @Schema(description = "공지사항 ID(공백)", example = "0")
    private int boardId;                            // 공지사항 코드
    @Schema(description = "공지사항 제목", example = "공지사항 제목 저장완료")
    private String title;                           // 제목
    @Schema(description = "공지사항 내용", example = "공지사항 내용 저장완료")
    private String content;                         // 내용
    @Schema(description = "사용자 ID", example = "47")
    private String memberId;                           // 사용자코드
    @Schema(description = "사용자 이름(공백)", example = "")
    private String memberName;                      // 사용자 이름
    @Schema(description = "생성일(공백)", example = "")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime createdAt;                // 생성일
    @Schema(description = "수정일(공백)", example = "")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime updatedAt;                // 마지막수정

    public static BoardSaveDto from (BoardSaveVo boardsavevo){
        return BoardSaveDto.builder()
                .boardId(boardsavevo.getBoardId())
                .title(boardsavevo.getTitle())
                .content(boardsavevo.getContent())
                .memberId(boardsavevo.getMemberId())
                .memberName(boardsavevo.getMemberName())
                .createdAt(boardsavevo.getCreatedAt())
                .updatedAt(boardsavevo.getUpdatedAt())
                .build();
    }


}
