package com.sphong.esmanager.controller;

import com.sphong.esmanager.config.HelmExecutor;
import jdk.nashorn.internal.objects.annotations.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class HelmController {
    @Autowired
    private HelmExecutor helmExecutor;

    @GetMapping("/helm")
    public String helmRun() throws IOException {
        String cmd = "helm repo list";
        return helmExecutor.run(cmd);
    }
}
