package com.ssafy.home.Heo.house.service;

import com.ssafy.home.Heo.house.dto.out.HouseDetailResponseDto;
import com.ssafy.home.Heo.house.repository.HouseDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class HouseServiceImpl implements HouseService {
    private final HouseDao dao;

    @Override
    public HouseDetailResponseDto findHouseByAptSeq(String aptSeq) throws SQLException {
        return dao.findHouseByAptSeq(aptSeq);
    }
}
