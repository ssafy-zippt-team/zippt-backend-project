package com.ssafy.home.Heo.house.service;

import com.ssafy.home.Heo.common.base.BaseResponseStatus;
import com.ssafy.home.Heo.common.exception.BaseException;
import com.ssafy.home.Heo.common.page.PageRequestDto;
import com.ssafy.home.Heo.common.page.PageResponseDto;
import com.ssafy.home.Heo.house.condition.SearchCondition;
import com.ssafy.home.Heo.house.dto.out.*;
import com.ssafy.home.Heo.house.entity.HouseEntity;
import com.ssafy.home.Heo.house.repository.HouseDao;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class HouseServiceImpl implements HouseService {
    private final HouseDao dao;

    @Override
    public HouseDetailResponseDto findHouseByAptSeq(String aptSeq) throws SQLException {
        HouseEntity entity = dao.findHouseByAptSeq(aptSeq);
        if(entity == null) throw new BaseException(BaseResponseStatus.NO_EXIST_HOUSE);
        return HouseDetailResponseDto.from(entity);
    }

    @Override
    public PageResponseDto<HouseResponseDto> getHouseList(PageRequestDto pageRequestDto) throws SQLException {
        // 1. 목록 조회
        List<HouseEntity> list = dao.getHouseList(pageRequestDto);
        if(list.isEmpty()) throw new BaseException(BaseResponseStatus.NO_EXIST_HOUSE); // 결과 없으면 에러 메시지
        // 2. 전체 개수 조회
        int totalCount = dao.getHouseListCount();
        // 3. 응답 조립
        return PageResponseDto.<HouseResponseDto> withAll()
                .dtoList(list.stream()
                        .map(HouseResponseDto::from)
                        .collect(Collectors.toList()))
                .totalCount(totalCount)
                .pageRequestDTO(pageRequestDto)
                .build();
    }

    @Override
    public PageResponseDto<HouseResponseDto> findHousesByCondition(SearchCondition searchCondition) throws SQLException {
        List <HouseEntity> list = dao.findHousesByCondition(searchCondition);
        int totalCount = dao.getHouseCountByCondition(searchCondition);
        return PageResponseDto.<HouseResponseDto> withAll()
                .dtoList(list.stream().map(HouseResponseDto::from).collect(Collectors.toList()))
                .totalCount(totalCount)
                .pageRequestDTO(searchCondition)
                .build();
    }

    @Override
    public List<HouseMarkerResponseDto> findAllHousesByDong(String sggCd, String umdCd) throws SQLException {
        // 1. 아파트 기본 정보 조회
        List<HouseMarkerResponseDto> list = dao.findAllHousesByDong(sggCd, umdCd);

        // 2. aptSeq 리스트 추출
        List<String> aptSeqList = list.stream()
                .map(HouseMarkerResponseDto::getAptSeq)
                .toList();

        // 3. aptSeq 리스트로 평균, 최고, 최저 거래 금액 조회
        List<HouseDealAmountInfoResponseDto> dealList = dao.findAllHouseDealAvgByAptSeqList(aptSeqList);
        Map<String, HouseDealAmountInfoResponseDto> dealMap = dealList.stream()
                .collect(Collectors.toMap(HouseDealAmountInfoResponseDto::getAptSeq, dto -> dto));

        // 4. aptSeq 리스트로 북마크 수 조회
        List<BookmarkCountDto> bookmarkList = dao.findAllBookmarkCountByAptSeqList(aptSeqList);
        Map<String, Integer> bookmarkMap = bookmarkList.stream()
                .collect(Collectors.toMap(BookmarkCountDto::getAptSeq, BookmarkCountDto::getCount));

        // 5. 원본 list에 모든 정보 반영 (조립)
        list.forEach(dto -> {
            // 거래 금액 정보 업데이트
            HouseDealAmountInfoResponseDto deal = dealMap.get(dto.getAptSeq());
            if (deal != null) {
                dto.setAmountAvg(deal.getAmountAvg());
                dto.setAmountMax(deal.getAmountMax());
                dto.setAmountMin(deal.getAmountMin());
            }
            // 즐겨찾기 수 업데이트 
            Integer bookmarkCount = bookmarkMap.get(dto.getAptSeq());
            dto.setBookMarkCount(bookmarkCount != null ? bookmarkCount : 0);
        });

        return list;
    }
}
