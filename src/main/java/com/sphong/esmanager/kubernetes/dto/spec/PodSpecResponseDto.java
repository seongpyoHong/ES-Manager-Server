package com.sphong.esmanager.kubernetes.dto.spec;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PodSpecResponseDto {
    private String nodeName;
    private String restartPolicy;
    private ContainerSpecResponseDto containers;

    @Builder
    public PodSpecResponseDto(String nodeName, String restartPolicy, ContainerSpecResponseDto containers) {
        this.nodeName = nodeName;
        this.restartPolicy = restartPolicy;
        this.containers = containers;
    }
}
