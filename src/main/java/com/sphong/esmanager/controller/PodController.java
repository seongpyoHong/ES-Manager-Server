package com.sphong.esmanager.controller;

import com.sphong.esmanager.dto.kubernetes.PodMetadataResponseDto;
import com.sphong.esmanager.utils.KubernetesManager;
import io.kubernetes.client.openapi.ApiException;
import io.kubernetes.client.openapi.models.V1ObjectMeta;
import io.kubernetes.client.openapi.models.V1PodSpec;
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
}
