package com.sphong.esmanager.dto.kubernetes.spec;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ContainerSpecResponseDto {
    private String image;
    private String imagePullPolicy;
    private ResourceResponseDto resources;
    private ReadinessProbeResponseDto readinessProbe;

    @Builder
    public ContainerSpecResponseDto(String image, String imagePullPolicy, ResourceResponseDto resources, ReadinessProbeResponseDto readinessProbe) {
        this.image = image;
        this.imagePullPolicy = imagePullPolicy;
        this.resources = resources;
        this.readinessProbe = readinessProbe;
    }
}
