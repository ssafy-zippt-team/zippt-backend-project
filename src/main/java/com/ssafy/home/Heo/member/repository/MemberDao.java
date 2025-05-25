package com.ssafy.home.Heo.member.repository;

import com.ssafy.home.Heo.bookmark.dto.out.BookmarkResponseDto;
import com.ssafy.home.Heo.member.dto.in.MemberRequestDto;
import com.ssafy.home.Heo.member.dto.out.MemberResponseDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;

@Mapper
@Repository
public interface MemberDao {

    // 마이페이지 조회
    MemberResponseDto getMember(String memberUuid) throws SQLException;

    void memberUpdate(MemberRequestDto dto) throws SQLException;

}
