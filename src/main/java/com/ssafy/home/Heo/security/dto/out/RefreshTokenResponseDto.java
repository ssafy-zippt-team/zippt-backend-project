package com.ssafy.home.Heo.security.dto.out;

import com.ssafy.home.Heo.security.entity.RefreshTokenEntity;
import lombok.Builder;
import lombok.Getter;

import java.util.Date;

@Getter
@Builder
public class RefreshTokenResponseDto {
    private int id;
    private String uuid;
    private String token;
    private Date expiresAt;

    public static RefreshTokenResponseDto from(RefreshTokenEntity entity){
        return RefreshTokenResponseDto.builder()
                .id(entity.getId())
                .uuid(entity.getUuid())
                .token(entity.getToken())
                .expiresAt(entity.getExpiresAt())
                .build();
    }
}
