package com.ssafy.home.Heo.security.service;

import com.ssafy.home.Heo.security.entity.RefreshTokenEntity;
import com.ssafy.home.Heo.security.jwt.JWTUtil;
import com.ssafy.home.Heo.security.repository.RefreshTokenDao;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class RefreshTokenService {

    private final RefreshTokenDao refreshTokenDao;
    private final JWTUtil jwtUtil;

    public RefreshTokenService(RefreshTokenDao refreshTokenDao, JWTUtil jwtUtil) {
        this.refreshTokenDao = refreshTokenDao;
        this.jwtUtil = jwtUtil;
    }

    /**
     * DB에 저장된 refresh token과 전달받은 토큰을 비교하고,
     * JWTUtil.isExpired로 만료 여부까지 검사합니다.
     */
    public boolean isValid(String uuid, String refreshToken) {
        // 1) 서명 및 만료(exp) 검증
        try {
            jwtUtil.parseClaims(refreshToken);
        } catch (Exception ex) {
            // 서명 불일치나 만료된 토큰인 경우
            return false;
        }

        // 2) DB에서 현재 저장된 리프레시 토큰 조회
        Optional<RefreshTokenEntity> tokenOpt = refreshTokenDao.findByUuid(uuid);
        if (tokenOpt.isEmpty()) {
            return false;
        }
        RefreshTokenEntity entity = tokenOpt.get();
//        System.out.println("DB's token : " + entity);

//        String storedToken = tokenOpt.get().getToken();
//        System.out.println("[isValid] cookieToken='"+ refreshToken +"'");
//        System.out.println("[isValid] storedToken='"+ storedToken +"'");
//        System.out.println("[isValid] equals? " + storedToken.equals(refreshToken));
        // 3) 쿠키에서 넘어온 토큰 문자열과 DB에 저장된 토큰이 **완전히 일치**해야 함
        if (!entity.getToken().equals(refreshToken)) {
            return false;
        }

        // 4) DB에 기록된 만료 시간(expiredAt)으로도 한 번 더 만료 검증
//        System.out.println("expires at : "+entity.getExpiresAt());
        if (entity.getExpiresAt().before(new Date())) {
            // 만료된 토큰은 DB에서 삭제
            refreshTokenDao.deleteByUuid(uuid);
            return false;
        }

        // 모든 검증 통과
        return true;
    }


    /**
     * 로그아웃 등 필요 시 DB에서 refresh token 삭제
     */
    public void deleteToken(String uuid) {
        refreshTokenDao.deleteByUuid(uuid);
    }

    /**
     * 로그인 시, 혹은 refresh 토큰 재발급 시 호출
     */
    public void saveToken(String uuid, String refreshToken) {
        // 기존 토큰 삭제
        refreshTokenDao.deleteByUuid(uuid);

        // 새 토큰 저장
//        System.out.println("Service's refreshToken= " + refreshToken);
//        System.out.println("Service's userId= "+ uuid);
        refreshTokenDao.insertRefreshToken(uuid, refreshToken, new Date(System.currentTimeMillis() + jwtUtil.getRefreshExpiredMs()));
    }
    /**
     * 토큰 탈취 대비한 블랙리스트 생성
     */

}