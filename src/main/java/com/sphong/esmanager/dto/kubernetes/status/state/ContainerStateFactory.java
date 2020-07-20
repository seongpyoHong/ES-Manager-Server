package com.sphong.esmanager.dto.kubernetes.status.state;

import io.kubernetes.client.openapi.models.V1ContainerState;

public class ContainerStateFactory {
    public static ContainerState makeContainerState(V1ContainerState v1ContainerState) throws IllegalContainerStateException {
        if (v1ContainerState.getRunning() != null) {
            return new ContainerStateRunning(v1ContainerState);
        } else if (v1ContainerState.getWaiting() != null) {
            return new ContainerStateWaiting(v1ContainerState);
        } else if (v1ContainerState.getTerminated() != null) {
            return new ContainerStateTerminated(v1ContainerState);
        }
        throw new IllegalContainerStateException("Container State Not Found!");
    }
}
