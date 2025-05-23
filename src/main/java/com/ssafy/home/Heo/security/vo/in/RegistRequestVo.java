package com.ssafy.home.Heo.security.vo.in;

import com.ssafy.home.Heo.security.dto.in.RegistRequestDto;
import lombok.Builder;
import lombok.Getter;

//@Setter
@Getter
@Builder
public class RegistRequestVo {
    private String nickname;
    private String username;
    private String userEmail;
    private String password;
    private String phoneNumber;

    public static RegistRequestDto from(RegistRequestVo vo){

        return RegistRequestDto.builder()
                .nickname(vo.nickname)
                .username(vo.username)
                .userEmail(vo.userEmail)
                .password(vo.password)
                .phoneNumber(vo.phoneNumber)
                .build();

    }

}
