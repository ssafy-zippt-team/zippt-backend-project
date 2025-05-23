package com.ssafy.home.Heo.security.entity;

import lombok.*;
import org.apache.ibatis.type.Alias;

@Alias("UserEntity")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class UserEntity {
    private int id;
    private String uuid;
    private String nickname;
    private String username;
    private String userEmail;
    private String password;
    private String phoneNumber;
    private String role;
}
