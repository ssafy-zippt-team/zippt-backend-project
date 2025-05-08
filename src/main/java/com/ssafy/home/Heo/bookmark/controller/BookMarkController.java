package com.ssafy.home.Heo.bookmark.controller;

import com.ssafy.home.Heo.bookmark.dto.out.BookmarkResponseDto;
import com.ssafy.home.Heo.bookmark.entity.BookmarkEntity;
import com.ssafy.home.Heo.bookmark.service.BookmarkService;
import com.ssafy.home.Heo.bookmark.vo.in.BookmarkSaveVo;
import io.swagger.v3.oas.annotations.Operation;
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
    @GetMapping("/list/{memberId}")
    public ResponseEntity<?> list(@PathVariable(name = "memberId")String memberId) throws SQLException {
        List<BookmarkResponseDto> list = service.getBookmarkList(memberId);
        return ResponseEntity.ok(list);
    }
    /*==============================================================
        즐겨찾기 전체 조회 END
    ==============================================================*/
    /*==============================================================
        즐겨찾기 저장
    ==============================================================*/
    @Operation(summary = "즐겨찾기 저장", description = "즐겨찾기 저장", tags = {"bookmark"})
    @PostMapping("/save")
    public ResponseEntity<Void> insert(@ParameterObject BookmarkSaveVo bookmarksavevo) throws SQLException {
        System.out.println("Insert 객체 받아오기 bookmark = " + bookmarksavevo);
        service.insert(bookmarksavevo.from(bookmarksavevo));
        return ResponseEntity.ok().build();
    }
    /*==============================================================
        즐겨찾기 저장 END
    ==============================================================*/
    /*==============================================================
        즐겨찾기 삭제
    ==============================================================*/
    @Operation(summary = "즐겨찾기 삭제", description = "즐겨찾기 삭제", tags = {"bookmark"})
    @DeleteMapping("/{bookmarkId}")
    public ResponseEntity<Void> delete(@PathVariable(name = "bookmarkId")String bookmarkId) throws SQLException {
        System.out.println("bookmarkId = " + bookmarkId);
        service.delete(bookmarkId);
        return ResponseEntity.ok().build();
    }
    /*==============================================================
        즐겨찾기 삭제 END
    ==============================================================*/


}
