package com.sphong.esmanager.controller;

import com.sphong.esmanager.dto.kubernetes.metadata.PodMetadataResponseDto;
import com.sphong.esmanager.dto.kubernetes.spec.PodSpecResponseDto;
import com.sphong.esmanager.dto.kubernetes.status.PodStatusResponseDto;
import com.sphong.esmanager.utils.KubernetesManager;
import io.kubernetes.client.openapi.ApiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PodController {
    @Autowired
    private KubernetesManager kubernetesManager;

    @GetMapping("/get-pods-metadata")
    public List<PodMetadataResponseDto> getAllPodMetadata() throws ApiException {
        return kubernetesManager.getPodMetadataList();
    }

    @GetMapping("/get-pods-spec")
    public List<PodSpecResponseDto> getAllPodSpec() throws ApiException {
        return kubernetesManager.getPodSpecList();
    }

    @GetMapping("/get-pods-status-history")
    public List<PodStatusResponseDto> getPodStatus() throws ApiException {
        return kubernetesManager.getPodStatusHistoryList();
    }
}

