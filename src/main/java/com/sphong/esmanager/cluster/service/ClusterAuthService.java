package com.sphong.esmanager.cluster.service;

import com.sphong.esmanager.common.CommandExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class ClusterAuthService {

    @Autowired
    private CommandExecutor commandExecutor;

    public String getcloudAuth(String projectName, String clusterName, String nodeRegion) throws IOException, InterruptedException {
        activateServiceAccount(projectName);
        setPorject(projectName);
        setNodeRegion(nodeRegion);
        getClusterCredentials(clusterName);
        return projectName;
    }

    private void activateServiceAccount(String projectName) throws IOException, InterruptedException {
        commandExecutor.run("gcloud auth activate-service-account --key-file=credential/" + projectName + ".json");
    }

    private void setPorject(String projectName) throws IOException, InterruptedException {
        commandExecutor.run("gcloud config set project "+ projectName);
    }

    private void setNodeRegion(String nodeRegion) throws IOException, InterruptedException {
        commandExecutor.run("gcloud config set compute/zone " + nodeRegion);
    }

    private void getClusterCredentials(String clusterName) throws IOException, InterruptedException {
        commandExecutor.run("gcloud container clusters get-credential " + clusterName);
    }
}
