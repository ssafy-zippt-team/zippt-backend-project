package com.ssafy.home.Heo.security.repository;

import com.ssafy.home.Heo.security.entity.RefreshTokenEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Optional;

@Repository
@Mapper
public interface RefreshTokenDao {

    /**
     * refresh token 저장
     */
    void insertRefreshToken(String uuid, String refreshToken, Date expiresAt);

    /**
     * refresh token 갱신
     */
    void update(RefreshTokenEntity refreshTokenEntity);

    /**
     * 주어진 username 으로 저장된 RefreshToken 조회
     */
    Optional<RefreshTokenEntity> findByUuid(String uuid);

    /**
     * 주어진 username 으로 저장된 RefreshToken 레코드를 삭제
     */
    void deleteByUuid(String uuid);
}
