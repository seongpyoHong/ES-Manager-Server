package com.sphong.esmanager.helm.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class HelmValues {
    private CerebroConfig cerebro;
    private ElasticsearchConfig elasticsearch;
    private KibanaConfig kibana;
    private ClusterConfig cluster;
}
