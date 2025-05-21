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
import com.ssafy.home.Heo.review.dto.out.ReviewSimpleResponseDto;
import org.apache.ibatis.annotations.Param;

import java.sql.SQLException;
import java.util.List;

public interface ReviewService {

    // 회원의 리뷰 조회
    PageResponseDto<ReviewDetailResponseDto> getReviewList(PageRequestDto pageRequestDto, String memberUuid, String aptSeq) throws SQLException;

    //리뷰 등록
    public int insert(ReviewSaveDto reviewsavedto) throws SQLException;
    //리뷰 삭제
    public void delete(String boardId) throws SQLException;
    //리뷰 업데이트
    public void update(ReviewUpdateDto reviewupdatedto) throws SQLException;

    // 아파트의 리뷰 조회
    PageResponseDto<ReviewSimpleResponseDto> getReviewListByAptSeq(PageRequestDto pageRequestDto, String aptSeq) throws SQLException;

    List<ReviewSimpleResponseDto> getNReviewListByAptSeq(String aptSeq, Integer limit) throws SQLException;



}
