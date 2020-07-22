package com.sphong.esmanager.kubernetes.utils;

import com.sphong.esmanager.kubernetes.dto.metadata.PodMetadataResponseDto;
import io.kubernetes.client.openapi.models.V1ObjectMeta;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Objects;

@Component
public class PodMetadataConverter implements Converter<V1ObjectMeta, PodMetadataResponseDto> {
    @Override
    public PodMetadataResponseDto convert(V1ObjectMeta source) {
        return PodMetadataResponseDto.builder()
                                        .name(source.getName())
                                        .namespace(source.getNamespace())
                                        .component(getComponent(Objects.requireNonNull(source.getLabels())))
                                        .creationTime(Objects.requireNonNull(source.getCreationTimestamp()).toString())
                                     .build();
    }

    private String getComponent(Map<String, String> labels) {
        return labels.get("component");
    }
}
