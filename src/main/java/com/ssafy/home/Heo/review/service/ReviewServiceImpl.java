package com.ssafy.home.Heo.review.service;

import com.ssafy.home.Heo.board.dto.in.BoardSaveDto;
import com.ssafy.home.Heo.board.dto.in.BoardUpdateDto;
import com.ssafy.home.Heo.board.dto.out.BoardDetailResponseDto;
import com.ssafy.home.Heo.board.dto.out.BoardResponseDto;
import com.ssafy.home.Heo.board.entity.BoardEntity;
import com.ssafy.home.Heo.board.repository.BoardDao;
import com.ssafy.home.Heo.board.vo.out.BoardResponseVo;
import com.ssafy.home.Heo.common.base.BaseResponseStatus;
import com.ssafy.home.Heo.common.exception.BaseException;
import com.ssafy.home.Heo.common.page.PageRequestDto;
import com.ssafy.home.Heo.common.page.PageResponseDto;
import com.ssafy.home.Heo.deal.dto.out.DealInfoResponseDto;
import com.ssafy.home.Heo.review.dto.in.ReviewSaveDto;
import com.ssafy.home.Heo.review.dto.in.ReviewUpdateDto;
import com.ssafy.home.Heo.review.dto.out.ReviewDetailResponseDto;
import com.ssafy.home.Heo.review.dto.out.ReviewSimpleResponseDto;
import com.ssafy.home.Heo.review.repository.ReviewDao;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class ReviewServiceImpl implements ReviewService {
    private final ReviewDao dao;

    /*==============================================================
      리뷰 상세 조회
    ==============================================================*/
    @Override
    public PageResponseDto<ReviewDetailResponseDto> getReviewList(PageRequestDto pageRequestDto, String memberUuid, String aptSeq) throws SQLException {
//        log.info("aptSeq = {}", aptSeq);
//        log.info("memberUuid = {}", memberUuid);
//        log.info("offset = {}", pageRequestDto.getOffset());
//        log.info("size = {}", pageRequestDto.getSize());
        List<ReviewDetailResponseDto> list = dao.getReviewList(pageRequestDto,memberUuid,aptSeq);
        log.info("list = {}", list);
        // 2. 전체 개수 조회
        int totalCount = dao.getReviewListCount();
        log.info("total = "+totalCount);
        System.out.println("totalCount = " + totalCount);
        // 3. 응답 조립
        return PageResponseDto.<ReviewDetailResponseDto> withAll()
                .dtoList(list)
                .totalCount(totalCount)
                .pageRequestDTO(pageRequestDto)
                .build();
    }
    /*==============================================================
        리뷰 조회 END
    ==============================================================*/
    /*==============================================================
        리뷰 등록
    ==============================================================*/
    @Override
    public int insert(ReviewSaveDto reviewsavedto) throws SQLException {
        int cnt = dao.insert(ReviewSaveDto.from(reviewsavedto));
        System.out.println("조회된 행 = " + cnt);
//        일단 보류
//        if (cnt == 0) throw new BaseException(BaseResponseStatus.NO_EXIST_BOARD);
        return cnt;
    }
    /*==============================================================
        리뷰 등록 END
    ==============================================================*/
     /*==============================================================
        리뷰 삭제
    ==============================================================*/
    @Override
    public void delete(String memberUuid) throws SQLException {
        dao.delete(memberUuid);
    }
    /*==============================================================
        리뷰 삭제 END
    ==============================================================*/
     /*==============================================================
        리뷰 업데이트
    ==============================================================*/
    @Override
    public void update(ReviewUpdateDto reviewupdatedto) throws SQLException {
        dao.update(ReviewUpdateDto.from(reviewupdatedto));
    }
        /*==============================================================
        리뷰 업데이트 END
    ==============================================================*/


    @Override
    public PageResponseDto<ReviewSimpleResponseDto> getReviewListByAptSeq(PageRequestDto pageRequestDto, String aptSeq) throws SQLException {
        List<ReviewSimpleResponseDto> list = dao.getReviewListByAptSeq(pageRequestDto, aptSeq);
        int totalCount = dao.getReviewCountByAptSeq(aptSeq);
        return PageResponseDto.<ReviewSimpleResponseDto> withAll()
                .dtoList(list)
                .totalCount(totalCount)
                .pageRequestDTO(pageRequestDto)
                .build();
    }

    @Override
    public List<ReviewSimpleResponseDto> getNReviewListByAptSeq(String aptSeq, Integer limit) throws SQLException {
        return dao.getNReviewListByAptSeq(aptSeq,limit);
    }

}