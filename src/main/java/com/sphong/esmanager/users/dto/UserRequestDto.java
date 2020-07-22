package com.sphong.esmanager.users.dto;

import lombok.*;

@NoArgsConstructor
@Getter
@Setter
public class UserRequestDto {
    private String email;
    private String password;
    private String projectId;
    private String dockerRegistry;

    @Builder
    public UserRequestDto(String email, String password, String projectId, String dockerRegistry) {
        this.email = email;
        this.password = password;
        this.projectId = projectId;
        this.dockerRegistry = dockerRegistry;
    }
}
