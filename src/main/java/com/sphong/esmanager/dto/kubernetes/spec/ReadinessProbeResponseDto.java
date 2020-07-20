package com.sphong.esmanager.dto.kubernetes.spec;

import com.sphong.esmanager.dto.kubernetes.spec.probe.Probe;
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
    private Probe probe;

    @Builder
    public ReadinessProbeResponseDto(Integer failureThreshold, Integer periodSeconds, Integer successThreshold, Integer timeoutSeconds, Probe probe) {
        this.failureThreshold = failureThreshold;
        this.periodSeconds = periodSeconds;
        this.successThreshold = successThreshold;
        this.timeoutSeconds = timeoutSeconds;
        this.probe = probe;
    }
}
