package com.sphong.esmanager.domain.helm;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ElasticsearchConfig {
    private String strategy;
    private ImageConfig image;
    private String clusterName;
    private Boolean persistenceEnable;
    private DeploymentConfig client;
    private DeploymentConfig master;
    private StatefulSetConfig hot;
    private StatefulSetConfig warm;

    @Builder
    public ElasticsearchConfig(String strategy, ImageConfig image, String clusterName, Boolean persistenceEnable, DeploymentConfig client, DeploymentConfig master, StatefulSetConfig hot, StatefulSetConfig warm) {
        this.strategy = strategy;
        this.image = image;
        this.clusterName = clusterName;
        this.persistenceEnable = persistenceEnable;
        this.client = client;
        this.master = master;
        this.hot = hot;
        this.warm = warm;
    }
}
