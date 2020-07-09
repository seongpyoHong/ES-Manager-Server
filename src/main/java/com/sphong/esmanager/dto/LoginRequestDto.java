package com.sphong.esmanager.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
public class LoginRequestDto {
    private String email;
    private String password;
}
