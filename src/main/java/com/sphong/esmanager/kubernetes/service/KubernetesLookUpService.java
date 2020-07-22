package com.sphong.esmanager.kubernetes.service;

import com.sphong.esmanager.kubernetes.utils.PodMetadataConverter;
import com.sphong.esmanager.kubernetes.utils.PodSpecConverter;
import com.sphong.esmanager.kubernetes.utils.PodStatusConverter;
import com.sphong.esmanager.kubernetes.dto.EndpointResponseDto;
import com.sphong.esmanager.kubernetes.dto.metadata.PodMetadataResponseDto;
import com.sphong.esmanager.kubernetes.dto.spec.PodSpecResponseDto;
import com.sphong.esmanager.kubernetes.dto.status.PodStatusResponseDto;
import com.sphong.esmanager.utils.KubernetesManager;
import io.kubernetes.client.openapi.ApiClient;
import io.kubernetes.client.openapi.ApiException;
import io.kubernetes.client.openapi.Configuration;
import io.kubernetes.client.openapi.apis.CoreV1Api;
import io.kubernetes.client.openapi.models.V1Service;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class KubernetesLookUpService {
    private final ApiClient kubeApiClient;
    private final PodMetadataConverter podMetadataConverter;
    private final PodSpecConverter podSpecConverter;
    private final PodStatusConverter podStatusConverter;
    private CoreV1Api api;

    public KubernetesLookUpService(ApiClient kubeApiClient, PodMetadataConverter podMetadataConverter, PodSpecConverter podSpecConverter, PodStatusConverter podStatusConverter) {
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
