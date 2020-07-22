package com.sphong.esmanager.kubernetes.dto.status.state;

import io.kubernetes.client.openapi.models.V1ContainerState;
import lombok.Getter;

@Getter
public class ContainerStateWaiting extends ContainerState{
    private String message;
    private String reason;

    protected ContainerStateWaiting(V1ContainerState v1ContainerState) {
        super(v1ContainerState);
        this.currentState = ContainerStateType.WAITING.getType();
        this.message = v1ContainerState.getWaiting().getMessage();
        this.reason = v1ContainerState.getWaiting().getReason();
    }
}
