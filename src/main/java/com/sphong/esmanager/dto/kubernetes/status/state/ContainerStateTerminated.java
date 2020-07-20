package com.sphong.esmanager.dto.kubernetes.status.state;

import io.kubernetes.client.openapi.models.V1ContainerState;
import lombok.Getter;

@Getter
public class ContainerStateTerminated extends ContainerState{
    private String exitCode;
    private String message;
    private String reason;
    private String finishedTime;

    protected ContainerStateTerminated(V1ContainerState v1ContainerState) {
        super(v1ContainerState);
        this.currentState = ContainerStateType.TERMINATED.getType();
        this.exitCode = v1ContainerState.getTerminated().getExitCode().toString();
        this.message = v1ContainerState.getTerminated().getMessage();
        this.reason = v1ContainerState.getTerminated().getReason();
        this.finishedTime = v1ContainerState.getTerminated().getFinishedAt().toString();
    }
}
