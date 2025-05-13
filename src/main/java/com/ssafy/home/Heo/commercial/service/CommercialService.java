package com.ssafy.home.Heo.commercial.service;

import com.ssafy.home.Heo.commercial.dto.in.CommercialRequestRadiusDto;
import com.ssafy.home.Heo.commercial.vo.in.CommercialRequestRadiusVo;

import java.util.List;

public interface CommercialService {

    public List<CommercialRequestRadiusVo> getCommercialInRadius(CommercialRequestRadiusDto dto);
}
