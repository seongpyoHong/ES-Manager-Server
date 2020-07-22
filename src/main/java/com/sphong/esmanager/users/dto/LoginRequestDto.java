package com.sphong.esmanager.users.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class LoginRequestDto {
    private String email;
    private String password;
}
