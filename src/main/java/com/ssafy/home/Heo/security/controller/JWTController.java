package com.ssafy.home.Heo.security.controller;

import com.ssafy.home.Heo.security.dto.out.CustomUserDetails;
import com.ssafy.home.Heo.security.service.JoinService;
import com.ssafy.home.Heo.security.service.RefreshTokenService;
import com.ssafy.home.Heo.security.vo.in.RegistRequestVo;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ResponseBody
@Log4j2
@RequestMapping("/api/v1")
public class JWTController {

    private final JoinService joinService;

    private final RefreshTokenService refreshTokenService;

    public JWTController(JoinService joinService, RefreshTokenService refreshTokenService) {

        this.joinService = joinService;
        this.refreshTokenService = refreshTokenService;
    }

    @Operation(summary = "로그아웃", description = "로그아웃", tags = {"회원"})
    @PostMapping("/logout")
    public ResponseEntity<?> logout(
            @AuthenticationPrincipal CustomUserDetails user,
            HttpServletResponse response
    ) {
//        System.out.println("call LogoutController.logout");
        refreshTokenService.deleteToken(user.getUserUuid());
        // 쿠키 만료 처리
        Cookie cookie = new Cookie("refreshToken", null);
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        return ResponseEntity.ok().build();
    }

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
