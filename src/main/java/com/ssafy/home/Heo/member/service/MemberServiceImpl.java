package com.ssafy.home.Heo.member.service;

import com.ssafy.home.Heo.bookmark.dto.out.BookmarkResponseDto;
import com.ssafy.home.Heo.bookmark.repository.BookmarkDao;
import com.ssafy.home.Heo.bookmark.service.BookmarkService;
import com.ssafy.home.Heo.member.dto.in.MemberRequestDto;
import com.ssafy.home.Heo.member.dto.out.MemberResponseDto;
import com.ssafy.home.Heo.member.repository.MemberDao;
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
public class MemberServiceImpl implements MemberService {
    private final MemberDao dao;
    /*==============================================================
       마이페이지 조회
    ==============================================================*/
    @Override
    public MemberResponseDto getMember(String memberUuid) throws SQLException {
        return dao.getMember(memberUuid);
    }
    /*==============================================================
      마이페이지 조회 END
    ==============================================================*/

    public void memberUpdate(MemberRequestDto dto) throws SQLException{
        dao.memberUpdate(dto);
    };

}
