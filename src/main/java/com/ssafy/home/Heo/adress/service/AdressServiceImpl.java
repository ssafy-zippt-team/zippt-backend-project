package com.ssafy.home.Heo.adress.service;

import com.ssafy.home.Heo.adress.dto.out.AdressResponseDto;
import com.ssafy.home.Heo.adress.entity.AdressEntity;
import com.ssafy.home.Heo.adress.repository.AdressDao;
import com.ssafy.home.Heo.board.dto.in.BoardSaveDto;
import com.ssafy.home.Heo.board.dto.in.BoardUpdateDto;
import com.ssafy.home.Heo.board.dto.out.BoardDetailResponseDto;
import com.ssafy.home.Heo.board.dto.out.BoardResponseDto;
import com.ssafy.home.Heo.board.repository.BoardDao;
import com.ssafy.home.Heo.board.service.BoardService;
import com.ssafy.home.Heo.common.base.BaseResponse;
import com.ssafy.home.Heo.common.base.BaseResponseStatus;
import com.ssafy.home.Heo.common.exception.BaseException;
import com.ssafy.home.Heo.common.page.PageRequestDto;
import com.ssafy.home.Heo.common.page.PageResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class AdressServiceImpl implements AdressService {
    private final AdressDao dao;
    /*==============================================================
        공지사항 전체 조회
    ==============================================================*/
    @Override
    public List<AdressResponseDto> getCityList(String citySeq) throws SQLException {
        // 1. 목록 조회
        List<AdressEntity> list = dao.getCityList(citySeq);


        return list.stream()
                .map(entity -> AdressResponseDto.builder()
                        .adressSeq(entity.getAdressSeq())
                        .citySeq(entity.getCitySeq())
                        .guSeq(entity.getGuSeq())
                        .dongSeq(entity.getDongSeq())
                        .cityName(entity.getCityName())
                        .guName(entity.getGuName())
                        .dongName(entity.getDongName())
                        .build())
                .collect(Collectors.toList());

    }
    /*==============================================================
      공지사항 전체 조회 END
    ==============================================================*/

}
