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
        시 조회
    ==============================================================*/
    @Override
    public List<AdressResponseDto> getCityList() throws SQLException {
        List<AdressEntity> list = dao.getCityList();
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
      시 조회 END
    ==============================================================*/
    /*==============================================================
        구 조회
    ==============================================================*/
    @Override
    public List<AdressResponseDto> getGuList(String citySeq) throws SQLException {
        List<AdressEntity> list = dao.getGuList(citySeq);
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
      구 조회 END
    ==============================================================*/
    /*==============================================================
        동 조회
    ==============================================================*/
    @Override
    public List<AdressResponseDto> getDongList(String citySeq, String guSeq) throws SQLException {
        List<AdressEntity> list = dao.getDongList(citySeq,guSeq);
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
      동 조회 END
    ==============================================================*/
}
