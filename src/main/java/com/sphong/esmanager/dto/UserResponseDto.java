package com.sphong.esmanager.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserResponseDto {
    private String email;
    private String dockerRegistry;
    private String projectId;

    @Builder
    public UserResponseDto(String email, String dockerRegistry, String projectId) {
        this.email = email;
        this.dockerRegistry = dockerRegistry;
        this.projectId = projectId;
    }
}
