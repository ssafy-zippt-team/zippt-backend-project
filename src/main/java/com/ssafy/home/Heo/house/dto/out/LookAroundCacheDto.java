package com.ssafy.home.Heo.house.dto.out;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LookAroundCacheDto {
    private List<HouseMarkerResponseDto> houseList;
    private List<HouseDealAmountInfoResponseDto> dealList;
}
