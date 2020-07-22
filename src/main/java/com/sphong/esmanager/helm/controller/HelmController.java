package com.sphong.esmanager.helm.controller;

import com.sphong.esmanager.helm.domain.HelmValues;
import com.sphong.esmanager.helm.service.HelmService;
import com.sphong.esmanager.common.CommandExecutor;
import org.springframework.beans.factory.annotation.Autowired;
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


    @PostMapping("/set-elasticsearch-config")
    public void writeESConfig(@RequestBody HelmValues helmValues) throws IOException {
        helmService.writeESConfig(helmValues);
    }

    @PostMapping("/install-elasticsearch")
    public String createES() throws IOException, InterruptedException {
        return helmService.createES();
    }

    @PostMapping("delete-elasticsearch")
    public String deleteES() throws IOException, InterruptedException {
        return helmService.deleteES();
    }
}
