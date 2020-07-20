package com.sphong.esmanager.config;

import com.sphong.esmanager.dto.kubernetes.status.ContainerStateResponseDto;
import com.sphong.esmanager.dto.kubernetes.status.ContainerStatusResponseDto;
import com.sphong.esmanager.dto.kubernetes.status.PodConditionResponseDto;
import com.sphong.esmanager.dto.kubernetes.status.PodStatusResponseDto;
import io.kubernetes.client.openapi.models.V1ContainerState;
import io.kubernetes.client.openapi.models.V1ContainerStatus;
import io.kubernetes.client.openapi.models.V1PodCondition;
import io.kubernetes.client.openapi.models.V1PodStatus;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class PodStatusConverter implements Converter<V1PodStatus, PodStatusResponseDto> {
    @Override
    public PodStatusResponseDto convert(V1PodStatus source) {
        return PodStatusResponseDto.builder()
                                    .conditions(getPodConditionList(source))
                                    .containerStatus(getContainerStatus(source.getContainerStatuses().get(0)))
                                    .hostIp(source.getHostIP())
                                    .phase(source.getPhase())
                                    .podIp(source.getPodIP())
                                    .startTime(source.getStartTime().toString())
                                  .build();
    }

    private List<PodConditionResponseDto> getPodConditionList(V1PodStatus v1PodStatus) {
        return v1PodStatus.getConditions().stream()
                                          .map(this::convertToPodConditionDto)
                                          .collect(Collectors.toList());
    }

    private PodConditionResponseDto convertToPodConditionDto(V1PodCondition v1PodCondition) {
        return PodConditionResponseDto.builder()
                                        .type(v1PodCondition.getType())
                                        .lastTransitionTime(v1PodCondition.getLastTransitionTime().toString())
                                        .message(Objects.requireNonNullElse(v1PodCondition.getMessage(), " "))
                                        .reason(Objects.requireNonNullElse(v1PodCondition.getReason(), " "))
                                        .status(v1PodCondition.getStatus())
                                      .build();
    }
    private ContainerStatusResponseDto getContainerStatus(V1ContainerStatus v1ContainerStatus) {
        return ContainerStatusResponseDto.builder()
                .state(getContainerState(v1ContainerStatus.getState()))
                .image(v1ContainerStatus.getImage())
                .name(v1ContainerStatus.getName())
                .ready(v1ContainerStatus.getReady())
                .restartCount(v1ContainerStatus.getRestartCount())
                .started(v1ContainerStatus.getStarted())
                .build();
    }

    private ContainerStateResponseDto getContainerState(V1ContainerState v1ContainerState) {
        StringBuilder currentState = new StringBuilder();
        StringBuilder details = new StringBuilder();
        if (v1ContainerState.getRunning() != null) {
            currentState.append("Running");
            details.append("Started Time : ").append(v1ContainerState.getRunning().getStartedAt().toString());
        } else if (v1ContainerState.getTerminated() != null) {
            currentState.append("Terminated");
            details.append("Exit Code : " ).append(v1ContainerState.getTerminated().getExitCode().toString())
                    .append("| Message : ").append(v1ContainerState.getTerminated().getMessage())
                    .append(" | Reason : ").append(v1ContainerState.getTerminated().getReason())
                    .append(" | Finished Time : ").append(v1ContainerState.getTerminated().getFinishedAt().toString());
        } else if (v1ContainerState.getWaiting() != null) {
            currentState.append("Waiting");
            details.append("Message : ").append(v1ContainerState.getWaiting().getMessage())
                    .append(" | Reason : ").append(v1ContainerState.getWaiting().getReason());
        }
        return ContainerStateResponseDto.builder().currentState(currentState.toString()).details(details.toString()).build();
    }


}
