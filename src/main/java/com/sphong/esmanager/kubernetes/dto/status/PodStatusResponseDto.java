package com.sphong.esmanager.kubernetes.dto.status;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class PodStatusResponseDto {
    private List<PodConditionResponseDto> conditions;
    private ContainerStatusResponseDto containerStatus;
    private String hostIp;
    private String podIp;
    private String phase;
    private String startTime;

    @Builder
    public PodStatusResponseDto(List<PodConditionResponseDto> conditions, ContainerStatusResponseDto containerStatus, String hostIp, String podIp, String phase, String startTime) {
        this.conditions = conditions;
        this.containerStatus = containerStatus;
        this.hostIp = hostIp;
        this.podIp = podIp;
        this.phase = phase;
        this.startTime = startTime;
    }
}
