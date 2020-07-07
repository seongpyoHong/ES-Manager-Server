package com.sphong.esmanager.service;

import com.sphong.esmanager.config.YamlWriter;
import com.sphong.esmanager.domain.helm.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class HelmService {

    @Autowired
    private YamlWriter yamlWriter;

    public void writeYaml(HelmValues helmValues) throws IOException {
        yamlWriter.writeYaml(helmValues);
//        createHelmValues();
    }


    private HelmValues createHelmValues() {
        ImageConfig cerebroImage = ImageConfig.getCerebroImage();
        CerebroConfig cerebro = CerebroConfig.builder().image(cerebroImage).username("admin").password("admin").port(9000).replicaCount(1).build();
        ClusterConfig clusterConfig = ClusterConfig.builder().managerEmail("manager@email").userEmail("user@email").build();

        ImageConfig elasticsearchImage = ImageConfig.getElasticsearchImage();
        DeploymentConfig master = DeploymentConfig.builder().additionalJavaOpts(" ").heapSize("512m").replicaCount(3).build();
        DeploymentConfig client = DeploymentConfig.builder().additionalJavaOpts(" ").heapSize("512m").replicaCount(1).build();
        StatefulSetConfig hot = StatefulSetConfig.builder().additionalJavaOpts(" ").heapSize("512m").replicaCount(1).storage("1Gi").build();
        StatefulSetConfig warm = StatefulSetConfig.builder().additionalJavaOpts(" ").heapSize("512m").replicaCount(1).storage("1Gi").build();

        ElasticsearchConfig elasticsearch = ElasticsearchConfig.builder().client(client).clusterName("clusterName").master(master).hot(hot).warm(warm).image(elasticsearchImage).persistenceEnable(true).strategy("RollingUpdate").build();

        ImageConfig kibanaConfig = ImageConfig.getKibanaImage();
        KibanaConfig kibana = KibanaConfig.builder().image(kibanaConfig).build();

        return HelmValues.builder().cerebro(cerebro).elasticsearch(elasticsearch).kibana(kibana).cluster(clusterConfig).build();
    }
}
