package com.sphong.esmanager.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class HelmValues {
    private CerebroConfig cerebro;
    private ElasticsearchConfig elasticsearch;
    private KibanaConfig kibana;
    private ClusterConfig cluster;

    @Builder
    public HelmValues(CerebroConfig cerebro, ElasticsearchConfig elasticsearch, KibanaConfig kibana, ClusterConfig cluster) {
        this.cerebro = cerebro;
        this.elasticsearch = elasticsearch;
        this.kibana = kibana;
        this.cluster = cluster;
    }
}
