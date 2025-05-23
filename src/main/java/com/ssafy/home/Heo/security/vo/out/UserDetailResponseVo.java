package com.ssafy.home.Heo.security.vo.out;

import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class UserDetailResponseVo {
    private int id;
    private String username;
    private String password;
    private String role;
}
