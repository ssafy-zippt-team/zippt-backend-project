package com.ssafy.home.Heo.address.service;

import com.ssafy.home.Heo.address.dto.out.AddressResponseDto;

import java.sql.SQLException;
import java.util.List;

public interface AddressService {
    //시 조회
    List<AddressResponseDto> getCityList() throws SQLException;
    //구 조회
    List<AddressResponseDto> getGuList(String citySeq) throws SQLException;
    //동 조회
    List<AddressResponseDto> getDongList(String citySeq, String guSeq) throws SQLException;

}
