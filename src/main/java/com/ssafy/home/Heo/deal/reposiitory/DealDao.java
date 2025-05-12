package com.ssafy.home.Heo.deal.reposiitory;

import com.ssafy.home.Heo.deal.condition.OrderCondition;
import com.ssafy.home.Heo.deal.condition.SearchCondition;
import com.ssafy.home.Heo.deal.dto.out.DealInfoResponseDto;
import com.ssafy.home.Heo.deal.entity.DealEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;

@Mapper
@Repository
public interface DealDao {
    List <DealInfoResponseDto> findDealsByCondition(SearchCondition searchCondition) throws SQLException;
    int getDealCount(SearchCondition searchCondition) throws SQLException;
}
