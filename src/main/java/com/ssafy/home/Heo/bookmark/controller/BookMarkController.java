package com.ssafy.home.Heo.bookmark.controller;

import com.ssafy.home.Heo.bookmark.dto.out.BookmarkResponseDto;
import com.ssafy.home.Heo.bookmark.entity.BookmarkEntity;
import com.ssafy.home.Heo.bookmark.service.BookmarkService;
import com.ssafy.home.Heo.bookmark.vo.in.BookmarkSaveVo;
import com.ssafy.home.Heo.common.base.BaseResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/api/v1/bookmarks")
public class BookMarkController {
    private final BookmarkService service;
    /*==============================================================
        즐겨찾기 전체 조회
    ==============================================================*/
    @Operation(summary = "즐겨찾기 조회", description = "즐겨찾기 조회", tags = {"즐겨찾기"})
    @GetMapping("/list/{memberUuid}")
    public BaseResponse<List<BookmarkResponseDto>> list(
            @Parameter(description = "memberUuid", example = "63f912c8-2b04-11f0-a5b7-0242ac110002")
            @PathVariable(name = "memberUuid")String memberuuid) throws SQLException {
        List<BookmarkResponseDto> list = service.getBookmarkList(memberuuid);
        return BaseResponse.of(list);
    }
    /*==============================================================
        즐겨찾기 전체 조회 END
    ==============================================================*/
    /*==============================================================
        즐겨찾기 횟수 조회
    ==============================================================*/
    @Operation(summary = "아파트별 즐겨찾기 횟수 조회", description = "아파트별 즐겨찾기 횟수 조회", tags = {"즐겨찾기"})
    @GetMapping("/getBookmarkCnt/{aptSeq}")
    public BaseResponse<Integer> getBookmarkCnt(
            @Parameter(description = "aptSeq", example = "11110-100")
            @PathVariable(name = "aptSeq")String aptSeq) throws SQLException {
        return BaseResponse.of(service.getBookmarkCnt(aptSeq));
    }
    /*==============================================================
        즐겨찾기 횟수 조회 END
    ==============================================================*/
    /*==============================================================
        즐겨찾기 저장
    ==============================================================*/
    @Operation(summary = "즐겨찾기 저장", description = "즐겨찾기 저장", tags = {"즐겨찾기"})
    @PostMapping("/")
    public BaseResponse<String> insert(@ParameterObject BookmarkSaveVo bookmarksavevo) throws SQLException {
        System.out.println("Insert 객체 받아오기 bookmark = " + bookmarksavevo);
        service.insert(bookmarksavevo.from(bookmarksavevo));
        return BaseResponse.of("즐겨찾기 저장에 성공하였습니다.");
    }
    /*==============================================================
        즐겨찾기 저장 END
    ==============================================================*/
    /*==============================================================
        즐겨찾기 삭제 (토글기능 도입으로 사용안함)
    ==============================================================*/
    @Operation(summary = "즐겨찾기 삭제", description = "즐겨찾기 삭제(토글기능 도입으로 사용안함)", tags = {"즐겨찾기"})
    @DeleteMapping("/{bookmarkId}")
    public BaseResponse<String> delete(
            @Parameter(description = "bookmarkId", example = "1")
            @PathVariable(name = "bookmarkId")String bookmarkId) throws SQLException {
        System.out.println("bookmarkId = " + bookmarkId);
        service.delete(bookmarkId);
        return BaseResponse.of("즐겨찾기 삭제 성공하였습니다.");
    }
    /*==============================================================
        즐겨찾기 삭제 END
    ==============================================================*/

    /*==============================================================
        즐겨찾기 토글
    ==============================================================*/
    @Operation(summary = "즐겨찾기 토글", description = "즐겨찾기 토글(삭제,삽입시 활성화)", tags = {"즐겨찾기"})
    @PatchMapping("/toggle/{bookmarkId}")
    public BaseResponse<String> toggle(@PathVariable String bookmarkId) throws SQLException {
        service.toggleActiveFlag(bookmarkId);
        return BaseResponse.of("즐겨찾기 토글 성공하였습니다.");
    }
    /*==============================================================
        즐겨찾기 토글 END
    ==============================================================*/

   /*==============================================================
        멤버의 아파트 즐겨찾기 여부 조회 START
    ==============================================================*/
   @Operation(summary = "즐겨찾기 여부 확인", description = "aptSeq, memberUuid로 매물 즐겨찾기 여부 확인", tags = {"즐겨찾기"})
   @GetMapping("/check")
   public BaseResponse<Boolean> checkBoolMark(
           @Parameter(description = "aptSeq", example = "11110-100", required = true)
           @ParameterObject String aptSeq,

           @Parameter(description = "memberUuid", example = "9ed7aacf-2b04-11f0-a5b7-0242ac110002", required = true)
           @ParameterObject String memberUuid

   ) throws SQLException {

       return BaseResponse.of(
               service.checkBoolMark(aptSeq,memberUuid)
       );
   }
    /*==============================================================
        멤버의 아파트 즐겨찾기 여부 조회 END
    ==============================================================*/

}
