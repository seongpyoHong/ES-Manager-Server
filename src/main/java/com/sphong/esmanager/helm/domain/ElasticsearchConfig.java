package com.sphong.esmanager.helm.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ElasticsearchConfig {
    private String strategy;
    private ImageConfig image;
    private String clusterName;
    private Boolean persistentEnable;
    private DeploymentConfig client;
    private DeploymentConfig master;
    private StatefulSetConfig hot;
    private StatefulSetConfig warm;
}
