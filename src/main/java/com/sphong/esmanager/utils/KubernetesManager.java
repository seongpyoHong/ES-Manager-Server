package com.sphong.esmanager.utils;

import com.sphong.esmanager.config.PodMetadataConverter;
import com.sphong.esmanager.config.PodSpecConverter;
import com.sphong.esmanager.dto.kubernetes.PodMetadataResponseDto;
import com.sphong.esmanager.dto.kubernetes.PodSpecResponseDto;
import io.kubernetes.client.openapi.ApiClient;
import io.kubernetes.client.openapi.ApiException;
import io.kubernetes.client.openapi.Configuration;
import io.kubernetes.client.openapi.apis.CoreV1Api;
import io.kubernetes.client.openapi.models.V1PodSpec;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class KubernetesManager {
    private final ApiClient kubeApiClient;
    private final PodMetadataConverter podMetadataConverter;
    private final PodSpecConverter podSpecConverter;
    private CoreV1Api api;

    public KubernetesManager(ApiClient kubeApiClient, PodMetadataConverter podMetadataConverter, PodSpecConverter podSpecConverter) {
        this.kubeApiClient = kubeApiClient;
        this.podMetadataConverter = podMetadataConverter;
        this.podSpecConverter = podSpecConverter;
        Configuration.setDefaultApiClient(kubeApiClient);
        api = new CoreV1Api();
    }

    public List<PodMetadataResponseDto> getPodMetadataList() throws ApiException {
        return api.listPodForAllNamespaces(null, null, null, "instance=elasticsearch-manager", null, null, null, null, null)
                .getItems()
                .stream()
                .map(item -> podMetadataConverter.convert(Objects.requireNonNull(item.getMetadata())))
                .collect(Collectors.toList());
    }

    public List<PodSpecResponseDto> getPodSpecList() throws ApiException {
        return api.listPodForAllNamespaces(null, null, null, "instance=elasticsearch-manager", null, null, null, null, null)
                .getItems()
                .stream()
                .map(item -> podSpecConverter.convert(Objects.requireNonNull(item.getSpec())))
                .collect(Collectors.toList());
    }

    //TODO:
    //  1. Pod List 조회 및 Pod 이름 및 속성 저장
    //      - 저장할 값 : createTimeStamp-millis , zone.id / name (pod-name) / namespace / labels (component)
    //  2. spec 설정 조회 (Customized Field)
    //      - container (image, imagePullPolicy, readinessProbe (), resource)
    //  3. Logs 조회
    //  4. Service 조회 (Cerebro / Kibana / ES-Client Endpoint 조회)
    //  5. Helm을 통해 배포하는 경우 배포 진행 상황 watch (= kubectl get pods -o wide -w)
}
