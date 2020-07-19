package com.sphong.esmanager.dto.kubernetes.status;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ContainerStateResponseDto {
    private String currentState;
    private String details;

    @Builder
    public ContainerStateResponseDto(String currentState, String details) {
        this.currentState = currentState;
        this.details = details;
    }
}
