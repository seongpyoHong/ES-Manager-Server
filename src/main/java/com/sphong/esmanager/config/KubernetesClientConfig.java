package com.sphong.esmanager.config;

import io.kubernetes.client.openapi.ApiClient;
import io.kubernetes.client.util.ClientBuilder;
import io.kubernetes.client.util.KubeConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.io.FileReader;
import java.io.IOException;

@Configuration
public class KubernetesClientConfig {
    private static final String KUBE_CONFIG_PATH = "/Users/seongpyo/.kube/config";
    @Bean
    public ApiClient apiClient() throws IOException {
        return ClientBuilder.kubeconfig(KubeConfig.loadKubeConfig(new FileReader(KUBE_CONFIG_PATH))).build();
    }
}
