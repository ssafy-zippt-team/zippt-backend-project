package com.ssafy.home.Heo.security.dto.in;

import com.ssafy.home.Heo.security.entity.RefreshTokenEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class IssueRefreshTokenDto {
    private int id;
    private String uuid;
    private String token;
    private Date expires;
    private Date created;

    public static RefreshTokenEntity from(IssueRefreshTokenDto dto){
        return RefreshTokenEntity.builder()
                .id(dto.id)
                .uuid(dto.uuid)
                .token(dto.token)
                .expiresAt(dto.expires)
                .createdAt(dto.created)
                .build();
    }
}
