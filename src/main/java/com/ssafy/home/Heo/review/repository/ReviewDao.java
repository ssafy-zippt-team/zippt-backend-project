package com.ssafy.home.Heo.review.repository;

import com.ssafy.home.Heo.board.dto.out.BoardDetailResponseDto;
import com.ssafy.home.Heo.board.dto.out.BoardResponseDto;
import com.ssafy.home.Heo.board.entity.BoardEntity;
import com.ssafy.home.Heo.common.page.PageRequestDto;
import com.ssafy.home.Heo.review.dto.out.ReviewDetailResponseDto;
import com.ssafy.home.Heo.review.dto.out.ReviewSimpleResponseDto;
import com.ssafy.home.Heo.review.entity.ReviewEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;

@Mapper
@Repository
public interface ReviewDao {

    // 회원이 쓴 리뷰 조회
    List<ReviewDetailResponseDto> getReviewList(@Param("pageRequestDto") PageRequestDto pageRequestDto,
                                                @Param("memberUuid") String memberUuid ,
                                                @Param("aptSeq") String aptSeq ) throws SQLException;

    // 아파트의 리뷰 카운트
    int getReviewCountByMemberandAptSeq(@Param("memberUuid") String memberUuid , @Param("aptSeq") String aptSeq) throws SQLException;

    // 아파트의 리뷰 카운트
    int getReviewCountByAptSeq(@Param("aptSeq") String aptSeq) throws SQLException;

    // 아파트의 리뷰 조회
    List<ReviewSimpleResponseDto> getReviewListByAptSeq(@Param("pageRequestDto") PageRequestDto pageRequestDto,
                                                        @Param("aptSeq") String aptSeq) throws SQLException;
    // 아파트의 리뷰 조회 (n개만 가져오기)
    List<ReviewSimpleResponseDto> getNReviewListByAptSeq(@Param("aptSeq") String aptSeq,
                                                         @Param("limit") Integer limit) throws SQLException;

    // 리뷰 조회 cnt 가져오기
    int getReviewListCount(String aptSeq) throws SQLException;

    // 리뷰 등록
    int insert(ReviewEntity reviewentity) throws SQLException;
    // 리뷰 삭제
    void delete(String memberUuid) throws  SQLException;
    // 리뷰 업데이트
    void update(ReviewEntity reviewentity) throws SQLException;


}
