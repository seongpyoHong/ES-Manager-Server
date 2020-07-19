package com.sphong.esmanager.config;

import com.sphong.esmanager.dto.kubernetes.spec.ContainerSpecResponseDto;
import com.sphong.esmanager.dto.kubernetes.spec.PodSpecResponseDto;
import com.sphong.esmanager.dto.kubernetes.spec.ReadinessProbeResponseDto;
import com.sphong.esmanager.dto.kubernetes.spec.ResourceResponseDto;
import io.kubernetes.client.openapi.models.V1Container;
import io.kubernetes.client.openapi.models.V1PodSpec;
import io.kubernetes.client.openapi.models.V1Probe;
import io.kubernetes.client.openapi.models.V1ResourceRequirements;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class PodSpecConverter implements Converter<V1PodSpec, PodSpecResponseDto> {

    @Override
    public PodSpecResponseDto convert(V1PodSpec source) {
        return PodSpecResponseDto.builder()
                .nodeName(source.getNodeName())
                .restartPolicy(source.getRestartPolicy())
                .containers(getContainerSpec(source.getContainers().get(0)))
                .build();
    }

    private ContainerSpecResponseDto getContainerSpec(V1Container v1Container) {
        return ContainerSpecResponseDto.builder()
                .image(v1Container.getImage())
                .imagePullPolicy(v1Container.getImagePullPolicy())
                .readinessProbe(getReadinessProbe(Objects.requireNonNull(v1Container.getReadinessProbe())))
                .resources(getResourceDto(Objects.requireNonNull(v1Container.getResources())))
                .build();
    }

    private ResourceResponseDto getResourceDto(V1ResourceRequirements v1ResourceRequirements) {
        return ResourceResponseDto.builder()
                                    .limits(Objects.requireNonNullElse(v1ResourceRequirements.getLimits(), " ").toString())
                                    .requests(Objects.requireNonNullElse(v1ResourceRequirements.getRequests(), " ").toString())
                                  .build();
    }

    private ReadinessProbeResponseDto getReadinessProbe(V1Probe v1Probe) {
        return ReadinessProbeResponseDto.builder()
                                            .failureThreshold(v1Probe.getFailureThreshold())
                                            .periodSeconds(v1Probe.getPeriodSeconds())
                                            .successThreshold(v1Probe.getSuccessThreshold())
                                            .timeoutSeconds(v1Probe.getTimeoutSeconds())
                                            .details(getReadinessProbeDetails(v1Probe))
                                            .build();
    }

    private String getReadinessProbeDetails(V1Probe v1Probe) {
        StringBuilder details = new StringBuilder();
        if (v1Probe.getHttpGet() != null) {
            details.append("httpGet | ")
                    .append(v1Probe.getHttpGet().getPath())
                    .append(":")
                    .append(v1Probe.getHttpGet().getPort().toString());
        } else if (v1Probe.getExec() != null) {
            details.append("Exec | ")
                    .append(v1Probe.getExec().getCommand().toString());
        } else if (v1Probe.getTcpSocket() != null){
            details.append("TCPSocket | port : ")
                    .append(v1Probe.getTcpSocket().getPort().toString());
        }
        return details.toString();
    }
}
