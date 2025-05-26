package com.ssafy.home.Heo.bookmark.service;

import com.ssafy.home.Heo.bookmark.dto.in.BookmarkSaveDto;
import com.ssafy.home.Heo.bookmark.dto.out.BookmarkResponseDto;
import com.ssafy.home.Heo.bookmark.entity.BookmarkEntity;
import com.ssafy.home.Heo.bookmark.repository.BookmarkDao;
import com.ssafy.home.Heo.common.page.PageRequestDto;
import com.ssafy.home.Heo.common.page.PageResponseDto;
import com.ssafy.home.Heo.review.dto.out.ReviewDetailResponseDto;
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
public class BookmarkServiceImpl implements BookmarkService {
    private final BookmarkDao dao;
    /*==============================================================
       즐겨찾기 조회
    ==============================================================*/
    @Override
    public PageResponseDto<BookmarkResponseDto> getBookmarkList(PageRequestDto pageRequestDto,String memberUuid) throws SQLException {
        List<BookmarkResponseDto> list = dao.getBookmarkList(pageRequestDto, memberUuid);
        log.info("list = {}", list);
        int totalCount = dao.getBookmarkListCnt(memberUuid);
        log.info("total = "+totalCount);
        System.out.println("totalCount = " + totalCount);
        // 3. 응답 조립
        return PageResponseDto.<BookmarkResponseDto> withAll()
                .dtoList(list)
                .totalCount(totalCount)
                .pageRequestDTO(pageRequestDto)
                .build();
    }
    /*==============================================================
      즐겨찾기 조회 END
    ==============================================================*/
    /*==============================================================
       즐겨찾기 횟수 조회
    ==============================================================*/
    @Override
    public Integer getBookmarkCnt(String aptSeq) throws SQLException {
        return dao.getBookmarkCnt(aptSeq);
    }
    /*==============================================================
      즐겨찾기 횟수 조회 END
    ==============================================================*/
    /*==============================================================
        즐겨찾기 삭제
    ==============================================================*/
    @Override
    public void delete(String bookmarkId) throws SQLException {
        dao.delete(bookmarkId);
    }

    @Override
    public boolean checkBoolMark(String aptSeq, String memberUuid) throws SQLException {
        return dao.checkBoolMark( aptSeq, memberUuid);
    }
    /*==============================================================
        즐겨찾기 삭제 END
    ==============================================================*/
    /*==============================================================
        즐겨찾기 토글
    ==============================================================*/
    public void toggleActiveFlag(String memberUuid, String aptSeq) throws SQLException {
        int cnt = dao.isExistsReview(memberUuid,aptSeq);
        System.out.println("memberUuid = " + memberUuid + ", aptSeq = " + aptSeq);
        System.out.println("cnt : " + cnt);

        // 존재를 하면 토글기능
        if (cnt > 0){
            System.out.println("존재함 : toggle" );
            dao.toggleActiveFlag(memberUuid,aptSeq);
        }else{
            System.out.println("존재하지 않아서 : insert" );
            // 없으면 밀어넣어주기
            dao.insert(memberUuid,aptSeq);
        }
    }
    /*==============================================================
        즐겨찾기 토글 END
    ==============================================================*/
}
