package com.sphong.esmanager.service;

import com.sphong.esmanager.config.CommandExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class GoogleAuthService {

    @Autowired
    private CommandExecutor commandExecutor;

    //gcloud auth activate-service-account --key-file=()
    //gcloud config set project projectname
    //gcloud config set compute/zone region
    //gcloud container clusters get-credential cluster-name

    public String getGcloudAuth(String projectName, String clusterName, String nodeRegion) throws IOException, InterruptedException {
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
