package com.sphong.esmanager.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class StatefulSetConfig {
    private Integer replicaCount;
    private String heapSize;
    private String additionalJavaOpts;
    private String storage;

    @Builder
    public StatefulSetConfig(Integer replicaCount, String heapSize, String additionalJavaOpts, String storage) {
        this.replicaCount = replicaCount;
        this.heapSize = heapSize;
        this.additionalJavaOpts = additionalJavaOpts;
        this.storage = storage;
    }

    public static StatefulSetConfig getHotNodeConfig(Integer replicaCount, String heapSize, String additionalJavaOpts, String storage) {
        return StatefulSetConfig.builder()
                                .additionalJavaOpts(additionalJavaOpts)
                                .heapSize(heapSize)
                                .replicaCount(replicaCount)
                                .storage(storage)
                                .build();
    }

    public static StatefulSetConfig getWarmNodeConfig(Integer replicaCount, String heapSize, String additionalJavaOpts, String storage) {
        return StatefulSetConfig.builder()
                .additionalJavaOpts(additionalJavaOpts)
                .heapSize(heapSize)
                .replicaCount(replicaCount)
                .storage(storage)
                .build();
    }
}
