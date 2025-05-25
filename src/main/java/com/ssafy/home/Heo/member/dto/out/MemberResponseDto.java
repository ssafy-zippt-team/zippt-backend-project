package com.ssafy.home.Heo.member.dto.out;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ssafy.home.Heo.address.vo.out.AddressResponseVo;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemberResponseDto {
    private String name;                           // 이름
    private String memberName;                     // 닉네임
    private String email;                          // 이메일
    private String phoneNumber;                    // 폰 번호
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private String createdAt;                      // 계정 생성일
}
