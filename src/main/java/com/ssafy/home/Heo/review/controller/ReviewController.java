package com.ssafy.home.Heo.review.controller;

import com.ssafy.home.Heo.board.dto.out.BoardDetailResponseDto;
import com.ssafy.home.Heo.board.dto.out.BoardResponseDto;
import com.ssafy.home.Heo.board.service.BoardService;
import com.ssafy.home.Heo.board.vo.in.BoardSaveVo;
import com.ssafy.home.Heo.board.vo.in.BoardUpdateVo;
import com.ssafy.home.Heo.board.vo.out.BoardDetailResponseVo;
import com.ssafy.home.Heo.common.base.BaseResponse;
import com.ssafy.home.Heo.common.page.PageRequestDto;
import com.ssafy.home.Heo.common.page.PageResponseDto;
import com.ssafy.home.Heo.review.dto.out.ReviewDetailResponseDto;
import com.ssafy.home.Heo.review.service.ReviewService;
import com.ssafy.home.Heo.review.vo.in.ReviewSaveVo;
import com.ssafy.home.Heo.review.vo.in.ReviewUpdateVo;
import com.ssafy.home.Heo.review.vo.out.ReviewDetailResponseVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@RestController
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/api/v1/reviews")
@Tag(name = "리뷰", description = "리뷰 관련 API")
public class ReviewController {
    private final ReviewService service;

    /*==============================================================
        리뷰 조회
    ==============================================================*/
    @Operation(summary = "회원이 작성한 리뷰 조회", description = "맴버별, 아파트별 리뷰 조회")
    @GetMapping("/{memberUuid}/{aptSeq}")
    public PageResponseDto<ReviewDetailResponseDto> list(
            @Parameter(description = "맴버 UUID", example = "63f912c8-2b04-11f0-a5b7-0242ac110002")
            @PathVariable(name = "memberUuid")String memberuuid,
            @Parameter(description = "아파트 내부코드", example = "11110-100")
            @PathVariable(name = "aptSeq")String aptSeq,
            @ParameterObject PageRequestDto pageRequestDto
            // 여기 에 추가
    ) throws SQLException {
        return service.getReviewList(pageRequestDto, memberuuid, aptSeq);
    }
    /*==============================================================
        리뷰 조회 END
    ==============================================================*/
    /*==============================================================
        리뷰 저장
    ==============================================================*/
    @Operation(summary = "리뷰 저장", description = "리뷰 저장", tags = {"리뷰"})
    @PostMapping("/save")
    public ResponseEntity<Void> insert(@ParameterObject ReviewSaveVo reviewsavevo) throws SQLException {
        System.out.println("Insert 객체 받아오기 reviewsavevo = " + reviewsavevo);
        int cnt = service.insert(ReviewSaveVo.from(reviewsavevo));
        return ResponseEntity.ok().build();
    }
    /*==============================================================
        리뷰 저장 END
    ==============================================================*/
    /*==============================================================
        리뷰 삭제
    ==============================================================*/
    @Operation(summary = "리뷰 삭제", description = "리뷰 삭제", tags = {"리뷰"})
    @DeleteMapping("/{reviewId}")
    public ResponseEntity<Void> delete(
            @Parameter(description = "리뷰 ID", example = "1")
            @PathVariable(name = "reviewId")String reviewId) throws SQLException {
        System.out.println("reviewId = " + reviewId);
        service.delete(reviewId);
        return ResponseEntity.ok().build();
    }
    /*==============================================================
        리뷰 삭제 END
    ==============================================================*/
    /*==============================================================
        리뷰 수정
    ==============================================================*/
    @Operation(summary = "리뷰 수정", description = "리뷰 수정", tags = {"리뷰"})
    @PatchMapping("/update")
    public ResponseEntity<Void> update(@ParameterObject ReviewUpdateVo reviewupdatevo) throws SQLException {
        System.out.println("객체 받아오기 reviewupdatevo = " + reviewupdatevo);
        service.update(ReviewUpdateVo.from(reviewupdatevo));
        return ResponseEntity.ok().build();
    }
    /*==============================================================
        리뷰 수정 END
    ==============================================================*/

}
