package com.sphong.esmanager.controller;

import com.sphong.esmanager.config.CommandExecutor;
import com.sphong.esmanager.domain.helm.HelmValues;
import com.sphong.esmanager.service.HelmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class HelmController {
    @Autowired
    private CommandExecutor commandExecutor;

    @Autowired
    private HelmService helmService;

    @GetMapping("/install")
    public String installHelm() throws IOException, InterruptedException {
        String cmd = "helm install elasticsearch helm/elasticsearch/";
        return commandExecutor.run(cmd);
    }

    @PostMapping("/helm-settings")
    public void writeYaml(@RequestBody HelmValues helmValues) throws IOException {
        helmService.writeYaml(helmValues);
    }
}
