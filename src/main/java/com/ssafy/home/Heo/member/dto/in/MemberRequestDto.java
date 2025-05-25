package com.ssafy.home.Heo.member.dto.in;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemberRequestDto {
    private String memberUuid;
    private String memberName;                     // 닉네임
    private String email;                          // 이메일
    private String phoneNumber;                    // 폰 번호
}
