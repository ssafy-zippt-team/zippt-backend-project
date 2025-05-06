package com.ssafy.home.Heo.house.service;

import com.ssafy.home.Heo.common.base.BaseResponseStatus;
import com.ssafy.home.Heo.common.exception.BaseException;
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
        HouseDetailResponseDto res =dao.findHouseByAptSeq(aptSeq);
        if(res == null) throw new BaseException(BaseResponseStatus.NO_EXIST_HOUSE);
        return res;
    }
}
