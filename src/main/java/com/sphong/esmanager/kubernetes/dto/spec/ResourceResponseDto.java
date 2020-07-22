package com.sphong.esmanager.kubernetes.dto.spec;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ResourceResponseDto {
    private String limits;
    private String requests;

    @Builder
    public ResourceResponseDto(String limits, String requests) {
        this.limits = limits;
        this.requests = requests;
    }
}
