package com.sphong.esmanager.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserRequestDto {
    private String email;
    private String password;
    private String projectId;
    private String dockerRegistry;
}
