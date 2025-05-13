package com.ssafy.home.Heo.address.repository;

import com.ssafy.home.Heo.address.entity.AddressEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;

@Mapper
@Repository
public interface AddressDao {

    // 시 조회
    List<AddressEntity> getCityList() throws SQLException;
    // 구 조회
    List<AddressEntity> getGuList(String citySeq) throws SQLException;
    // 동 조회
    List<AddressEntity> getDongList(String citySeq, String guSeq) throws SQLException;
}
