package com.ssafy.home.Heo.security.controller;

import com.ssafy.home.Heo.security.dto.out.CustomUserDetails;
import com.ssafy.home.Heo.security.jwt.JWTUtil;
import com.ssafy.home.Heo.security.service.CheckPasswordService;
import com.ssafy.home.Heo.security.service.JoinService;
import com.ssafy.home.Heo.security.service.RefreshTokenService;
import com.ssafy.home.Heo.security.vo.in.RegistRequestVo;
import io.jsonwebtoken.Claims;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.sql.SQLException;
import java.util.Map;

@RestController
@ResponseBody
@Log4j2
@RequestMapping("/api/v1")
public class JWTController {

    private final JWTUtil jwtUtil;

    private final JoinService joinService;

    private final RefreshTokenService refreshTokenService;

    private final CheckPasswordService checkPasswordService;

    public JWTController(JWTUtil jwtUtil, JoinService joinService, RefreshTokenService refreshTokenService, CheckPasswordService checkPasswordService) {
        this.jwtUtil = jwtUtil;
        this.joinService = joinService;
        this.refreshTokenService = refreshTokenService;
        this.checkPasswordService = checkPasswordService;
    }

    @Operation(summary = "로그아웃", description = "accessToken 로그아웃", tags = {"회원"})
    @PostMapping("/logout/aToken")
    public ResponseEntity<?> logout(
            @AuthenticationPrincipal CustomUserDetails user,
            HttpServletResponse response
    ) {
        refreshTokenService.deleteToken(user.getUserUuid());
        // 쿠키 만료 처리
        Cookie cookie = new Cookie("refreshToken", null);
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "로그아웃", description = "refreshToken 로그아웃", tags = {"회원"})
    @PostMapping("/logout/rToken")
    public ResponseEntity<?> logout(
            @CookieValue(name="refreshToken", required=true) String refreshToken,
            HttpServletResponse response
    ) {
        Claims claims = jwtUtil.parseClaims(refreshToken);
        System.out.println("logout to Refresh Token : " + claims);
        String uuid = claims.get("uuid", String.class);
        refreshTokenService.deleteToken(uuid);

        // 쿠키 만료 처리
        Cookie cookie = new Cookie("refreshToken", null);
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        return ResponseEntity.ok().build();
    }

    /*==============================================================
      비밀번호 확인
   ==============================================================*/
    @Operation(summary = "비밀번호 확인", description = "비밀번호 확인", tags = {"회원"})
    @PostMapping("/checkPassword")
    public ResponseEntity<?> checkPassword(
            @AuthenticationPrincipal CustomUserDetails user,  @RequestBody Map<String, String> body) throws SQLException {

        System.out.println("user : " + user.getUserUuid());
        String rawPassword = body.get("password");
        System.out.println("password : " + rawPassword);

        boolean ok = checkPasswordService.chekPassword(user.getUserUuid(), rawPassword);
        if (ok) {
            return ResponseEntity.ok().build();
        } else {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "비밀번호가 일치하지 않습니다."
            );
        }
    }
    /*==============================================================
        mypage 수정 END
    ==============================================================*/

    @Operation(summary = "회원 가입", description = "회원 가입", tags = {"회원"})
    @PostMapping("/join")
    public String joinProcess(@RequestBody RegistRequestVo vo) {

//        System.out.println("JoinController's nickname : "+vo.getNickname());
//        System.out.println("JoinController's username : "+vo.getUsername());
//        System.out.println("JoinController's userEmail : "+vo.getUserEmail());
//        System.out.println("JoinController's userPassword : "+vo.getPassword());
//        System.out.println("JoinController's phoneNumber : "+vo.getPhoneNumber());

        joinService.joinProcess(RegistRequestVo.from(vo));

        return "ok";
    }

}
