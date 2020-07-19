package com.sphong.esmanager.dto.kubernetes.spec;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ReadinessProbeResponseDto {
    private Integer failureThreshold;
    private Integer periodSeconds;
    private Integer successThreshold;
    private Integer timeoutSeconds;
    private String details;

    @Builder
    public ReadinessProbeResponseDto(Integer failureThreshold, Integer periodSeconds, Integer successThreshold, Integer timeoutSeconds, String details) {
        this.failureThreshold = failureThreshold;
        this.periodSeconds = periodSeconds;
        this.successThreshold = successThreshold;
        this.timeoutSeconds = timeoutSeconds;
        this.details = details;
    }
}
