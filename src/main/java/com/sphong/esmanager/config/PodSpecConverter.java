package com.sphong.esmanager.config;

import com.sphong.esmanager.dto.kubernetes.spec.ContainerSpecResponseDto;
import com.sphong.esmanager.dto.kubernetes.spec.PodSpecResponseDto;
import com.sphong.esmanager.dto.kubernetes.spec.ReadinessProbeResponseDto;
import com.sphong.esmanager.dto.kubernetes.spec.ResourceResponseDto;
import com.sphong.esmanager.dto.kubernetes.spec.probe.InvalidProbeTypeException;
import com.sphong.esmanager.dto.kubernetes.spec.probe.Probe;
import com.sphong.esmanager.dto.kubernetes.spec.probe.ProbeFactory;
import io.kubernetes.client.openapi.models.V1Container;
import io.kubernetes.client.openapi.models.V1PodSpec;
import io.kubernetes.client.openapi.models.V1Probe;
import io.kubernetes.client.openapi.models.V1ResourceRequirements;
import lombok.SneakyThrows;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class PodSpecConverter implements Converter<V1PodSpec, PodSpecResponseDto> {

    @SneakyThrows
    @Override
    public PodSpecResponseDto convert(V1PodSpec source) {
        return PodSpecResponseDto.builder()
                .nodeName(source.getNodeName())
                .restartPolicy(source.getRestartPolicy())
                .containers(getContainerSpec(source.getContainers().get(0)))
                .build();
    }

    private ContainerSpecResponseDto getContainerSpec(V1Container v1Container) throws InvalidProbeTypeException {
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

    private ReadinessProbeResponseDto getReadinessProbe(V1Probe v1Probe) throws InvalidProbeTypeException {
        return ReadinessProbeResponseDto.builder()
                                            .failureThreshold(v1Probe.getFailureThreshold())
                                            .periodSeconds(v1Probe.getPeriodSeconds())
                                            .successThreshold(v1Probe.getSuccessThreshold())
                                            .timeoutSeconds(v1Probe.getTimeoutSeconds())
                                            .probe(getProbe(v1Probe))
                                            .build();
    }

    private Probe getProbe(V1Probe v1Probe) throws InvalidProbeTypeException {
        return ProbeFactory.makeReadinessProbeType(v1Probe);
    }
}
