package com.sphong.esmanager.kubernetes.dto.status.state;

import io.kubernetes.client.openapi.models.V1ContainerState;
import lombok.Getter;

@Getter
public class ContainerStateRunning extends ContainerState {
    private String startedTime;

    protected ContainerStateRunning(V1ContainerState v1ContainerState) {
        super(v1ContainerState);
        this.currentState = ContainerStateType.RUNNING.getType();
        this.startedTime = v1ContainerState.getRunning().getStartedAt().toString();
    }
}
