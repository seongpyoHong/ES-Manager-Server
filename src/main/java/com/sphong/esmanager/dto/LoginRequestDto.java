package com.sphong.esmanager.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class LoginRequestDto {
    private String email;
    private String password;
}
