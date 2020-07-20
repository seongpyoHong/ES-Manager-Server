package com.sphong.esmanager.dto.kubernetes.status.state;

import io.kubernetes.client.openapi.models.V1ContainerState;

public abstract class ContainerState {
    public String currentState;
    protected V1ContainerState v1ContainerState;
    protected ContainerState(V1ContainerState v1ContainerState) {
        this.v1ContainerState = v1ContainerState;
    }
}
