package com.sphong.esmanager.service;

import com.sphong.esmanager.dto.EndpointResponseDto;
import com.sphong.esmanager.dto.kubernetes.metadata.PodMetadataResponseDto;
import com.sphong.esmanager.dto.kubernetes.spec.PodSpecResponseDto;
import com.sphong.esmanager.dto.kubernetes.status.PodStatusResponseDto;
import com.sphong.esmanager.utils.KubernetesManager;
import io.kubernetes.client.openapi.ApiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KubernetesLookUpService {
    @Autowired
    private KubernetesManager kubernetesManager;

    public List<PodStatusResponseDto> getPodStatusHistoryList() throws ApiException {
        return kubernetesManager.getPodStatusHistoryList();
    }

    public List<PodSpecResponseDto> getPodSpecList() throws ApiException {
        return kubernetesManager.getPodSpecList();
    }

    public List<PodMetadataResponseDto> getPodMetadataList() throws ApiException {
        return kubernetesManager.getPodMetadataList();
    }

    public List<EndpointResponseDto> getEndpoints() throws ApiException {
        return kubernetesManager.getEndpoints();
    }
}
