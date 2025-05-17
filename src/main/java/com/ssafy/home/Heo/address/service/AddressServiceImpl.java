package com.ssafy.home.Heo.address.service;

import com.ssafy.home.Heo.address.dto.out.AddressResponseDto;
import com.ssafy.home.Heo.address.entity.AddressEntity;
import com.ssafy.home.Heo.address.repository.AddressDao;
import io.swagger.v3.oas.annotations.Parameter;
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
public class AddressServiceImpl implements AddressService {
    private final AddressDao dao;

//    @Parameter(description = "공지사항 ID", example = "1")

    /*==============================================================
        시 조회
    ==============================================================*/
    @Override
    public List<AddressResponseDto> getCityList() throws SQLException {
        List<AddressEntity> list = dao.getCityList();
        return list.stream()
                .map(entity -> AddressResponseDto.builder()
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
    public List<AddressResponseDto> getGuList(String citySeq) throws SQLException {
        List<AddressEntity> list = dao.getGuList(citySeq);
        return list.stream()
                .map(entity -> AddressResponseDto.builder()
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
    public List<AddressResponseDto> getDongList(String citySeq, String guSeq) throws SQLException {
        List<AddressEntity> list = dao.getDongList(citySeq,guSeq);
        return list.stream()
                .map(entity -> AddressResponseDto.builder()
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
