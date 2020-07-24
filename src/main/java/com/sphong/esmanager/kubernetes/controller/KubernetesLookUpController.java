package com.sphong.esmanager.kubernetes.controller;

import com.sphong.esmanager.kubernetes.dto.EndpointResponseDto;
import com.sphong.esmanager.kubernetes.dto.metadata.PodMetadataResponseDto;
import com.sphong.esmanager.kubernetes.dto.spec.PodSpecResponseDto;
import com.sphong.esmanager.kubernetes.dto.status.PodStatusResponseDto;
import com.sphong.esmanager.kubernetes.service.KubernetesLookUpService;
import io.kubernetes.client.openapi.ApiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class KubernetesLookUpController {
    @Autowired
    private KubernetesLookUpService kubernetesLookUpService;

    @GetMapping("/pods/metadata")
    public List<PodMetadataResponseDto> getAllPodMetadata() throws ApiException {
        return kubernetesLookUpService.getPodMetadataList();
    }

    @GetMapping("/pods/spec")
    public List<PodSpecResponseDto> getAllPodSpec() throws ApiException {
        return kubernetesLookUpService.getPodSpecList();
    }

    @GetMapping("/pods/status")
    public List<PodStatusResponseDto> getPodStatus() throws ApiException {
        return kubernetesLookUpService.getPodStatusHistoryList();
    }

    @GetMapping("/endpoints")
    public List<EndpointResponseDto> getEndpoints() throws ApiException {
        return kubernetesLookUpService.getEndpoints();
    }
}

