package com.ssafy.home.Heo.member.service;

import com.ssafy.home.Heo.bookmark.dto.out.BookmarkResponseDto;
import com.ssafy.home.Heo.member.dto.in.MemberRequestDto;
import com.ssafy.home.Heo.member.dto.out.MemberResponseDto;

import java.sql.SQLException;
import java.util.List;

public interface MemberService {
    public MemberResponseDto getMember(String memberUuid) throws SQLException;

    public void memberUpdate(MemberRequestDto dto) throws SQLException;
}
