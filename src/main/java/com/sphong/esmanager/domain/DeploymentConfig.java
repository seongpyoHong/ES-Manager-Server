package com.sphong.esmanager.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class DeploymentConfig {
    private Integer replicaCount;
    private String heapSize;
    private String additionalJavaOpts;

    @Builder
    public DeploymentConfig(Integer replicaCount, String heapSize, String additionalJavaOpts) {
        this.replicaCount = replicaCount;
        this.heapSize = heapSize;
        this.additionalJavaOpts = additionalJavaOpts;
    }

    public static DeploymentConfig getMasterConfig(String additionalJavaOpts, String heapSize, Integer replicaCount) {
        return DeploymentConfig.builder()
                                .additionalJavaOpts(additionalJavaOpts)
                                .heapSize(heapSize)
                                .replicaCount(replicaCount)
                                .build();
    }

    public static DeploymentConfig getClientConfig(String additionalJavaOpts, String heapSize, Integer replicaCount) {
        return DeploymentConfig.builder()
                .additionalJavaOpts(additionalJavaOpts)
                .heapSize(heapSize)
                .replicaCount(replicaCount)
                .build();
    }
}
