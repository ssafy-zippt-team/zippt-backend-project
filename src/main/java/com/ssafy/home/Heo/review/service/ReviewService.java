package com.ssafy.home.Heo.review.service;

import com.ssafy.home.Heo.board.dto.in.BoardSaveDto;
import com.ssafy.home.Heo.board.dto.in.BoardUpdateDto;
import com.ssafy.home.Heo.board.dto.out.BoardDetailResponseDto;
import com.ssafy.home.Heo.board.dto.out.BoardResponseDto;
import com.ssafy.home.Heo.common.page.PageRequestDto;
import com.ssafy.home.Heo.common.page.PageResponseDto;
import com.ssafy.home.Heo.review.dto.in.ReviewSaveDto;
import com.ssafy.home.Heo.review.dto.in.ReviewUpdateDto;
import com.ssafy.home.Heo.review.dto.out.ReviewDetailResponseDto;

import java.sql.SQLException;

public interface ReviewService {

    //리뷰 조회
    PageResponseDto<ReviewDetailResponseDto> getReviewList(PageRequestDto pageRequestDto, String aptSeq) throws SQLException;

    //리뷰 등록
    public int insert(ReviewSaveDto reviewsavedto) throws SQLException;
    //리뷰 삭제
    public void delete(String boardId) throws SQLException;
    //리뷰 업데이트
    public void update(ReviewUpdateDto reviewupdatedto) throws SQLException;
}
