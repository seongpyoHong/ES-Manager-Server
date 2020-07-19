package com.sphong.esmanager.dto.kubernetes.status;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PodConditionResponseDto {
    private String lastTransitionTime;
    private String message;
    private String reason;
    private String status;
    private String type;

    @Builder
    public PodConditionResponseDto(String lastTransitionTime, String message, String reason, String status, String type) {
        this.lastTransitionTime = lastTransitionTime;
        this.message = message;
        this.reason = reason;
        this.status = status;
        this.type = type;
    }
}
