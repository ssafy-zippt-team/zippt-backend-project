package com.ssafy.home.Heo.adress.service;

import com.ssafy.home.Heo.adress.dto.out.AdressResponseDto;
import com.ssafy.home.Heo.adress.vo.out.AdressResponseVo;
import com.ssafy.home.Heo.board.dto.in.BoardSaveDto;
import com.ssafy.home.Heo.board.dto.in.BoardUpdateDto;
import com.ssafy.home.Heo.board.dto.out.BoardDetailResponseDto;
import com.ssafy.home.Heo.board.dto.out.BoardResponseDto;
import com.ssafy.home.Heo.common.base.BaseResponse;
import com.ssafy.home.Heo.common.page.PageRequestDto;
import com.ssafy.home.Heo.common.page.PageResponseDto;

import java.sql.SQLException;
import java.util.List;

public interface AdressService {
    //시 조회
    List<AdressResponseDto> getCityList() throws SQLException;
    //구 조회
    List<AdressResponseDto> getGuList(String citySeq) throws SQLException;
    //동 조회
    List<AdressResponseDto> getDongList(String citySeq, String guSeq) throws SQLException;

}
