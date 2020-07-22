package com.sphong.esmanager.kubernetes.dto.status;

import com.sphong.esmanager.kubernetes.dto.status.state.ContainerState;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ContainerStatusResponseDto {
    private String image;
    private String name;
    private Boolean ready;
    private Boolean started;
    private Integer restartCount;
    private ContainerState state;

    @Builder
    public ContainerStatusResponseDto(String image, String name, Boolean ready, Boolean started, Integer restartCount, ContainerState state) {
        this.image = image;
        this.name = name;
        this.ready = ready;
        this.started = started;
        this.restartCount = restartCount;
        this.state = state;
    }
}
