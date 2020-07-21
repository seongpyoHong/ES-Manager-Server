package com.sphong.esmanager.utils;

import com.sphong.esmanager.config.PodMetadataConverter;
import com.sphong.esmanager.config.PodSpecConverter;
import com.sphong.esmanager.config.PodStatusConverter;
import com.sphong.esmanager.dto.EndpointResponseDto;
import com.sphong.esmanager.dto.kubernetes.metadata.PodMetadataResponseDto;
import com.sphong.esmanager.dto.kubernetes.spec.PodSpecResponseDto;
import com.sphong.esmanager.dto.kubernetes.status.PodStatusResponseDto;
import io.kubernetes.client.openapi.ApiClient;
import io.kubernetes.client.openapi.ApiException;
import io.kubernetes.client.openapi.Configuration;
import io.kubernetes.client.openapi.apis.CoreV1Api;
import io.kubernetes.client.openapi.models.V1EndpointSubset;
import io.kubernetes.client.openapi.models.V1Endpoints;
import io.kubernetes.client.openapi.models.V1Service;
import org.springframework.stereotype.Component;
import sun.rmi.transport.Endpoint;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class KubernetesManager {
    private final ApiClient kubeApiClient;
    private final PodMetadataConverter podMetadataConverter;
    private final PodSpecConverter podSpecConverter;
    private final PodStatusConverter podStatusConverter;
    private CoreV1Api api;

    public KubernetesManager(ApiClient kubeApiClient, PodMetadataConverter podMetadataConverter, PodSpecConverter podSpecConverter, PodStatusConverter podStatusConverter) {
        this.kubeApiClient = kubeApiClient;
        this.podMetadataConverter = podMetadataConverter;
        this.podSpecConverter = podSpecConverter;
        this.podStatusConverter = podStatusConverter;
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

    public List<PodStatusResponseDto> getPodStatusHistoryList() throws ApiException {
        return api.listPodForAllNamespaces(null, null, null, "instance=elasticsearch-manager", null, null, null, null, null)
                .getItems()
                .stream()
                .map(item -> podStatusConverter.convert(Objects.requireNonNull(item.getStatus())))
                .collect(Collectors.toList());
    }

    public List<EndpointResponseDto> getEndpoints() throws ApiException {
         return api.listServiceForAllNamespaces(null, null, null, "instance=elasticsearch-manager", null, null, null, null, null)
            .getItems().stream()
                        .map(this::convertToEndpointDto)
                        .collect(Collectors.toList());
    }
    private EndpointResponseDto convertToEndpointDto(V1Service v1Service) {
        return EndpointResponseDto.builder()
                                    .loadBalancerIp(v1Service.getStatus().getLoadBalancer().getIngress().get(0).getIp().toString())
                                    .port(v1Service.getSpec().getPorts().get(0).getPort().intValue())
                                    .endpointPodName(v1Service.getSpec().getSelector().get("component"))
                                  .build();
  }
}
    //TODO:
    //  1. Pod List 조회 및 Pod 이름 및 속성 저장
    //      - 저장할 값 : createTimeStamp-millis , zone.id / name (pod-name) / namespace / labels (component) - ok
    //  2. spec 설정 조회 (Customized Field)
    //      - container (image, imagePullPolicy, readinessProbe (), resource) - ok
    //  3. Status 조회
    //      - conditions (Initialized / Ready / ConrainersReady / PodScheduled)
    //      - containerStatuses(image, name, ready, restartCount, started, state)
    //          - state : running / terminate / waiting (message, reason)
    //      - hostIP
    //      - PodIP
    //      - phase
    //  4. Logs 조회
    //  5. Service 조회 (Cerebro / Kibana / ES-Client Endpoint 조회)
    //  6. Helm을 통해 배포하는 경우 배포 진행 상황 watch (= kubectl get pods -o wide -w)

