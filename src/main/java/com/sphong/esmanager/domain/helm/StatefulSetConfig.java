package com.sphong.esmanager.domain.helm;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class StatefulSetConfig {
    private Integer replicaCount;
    private String heapSize;
    private String additionalJavaOpts;
    private String storage;
}
