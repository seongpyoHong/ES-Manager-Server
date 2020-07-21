package com.sphong.esmanager.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class EndpointResponseDto {
    private String loadBalancerIp;
    private String endpointPodName;
    private Integer port;

    @Builder
    public EndpointResponseDto(String loadBalancerIp, String endpointPodName, Integer port) {
        this.loadBalancerIp = loadBalancerIp;
        this.endpointPodName = endpointPodName;
        this.port = port;
    }
}
