package com.sphong.esmanager.service;

import com.sphong.esmanager.config.CommandExecutor;
import com.sphong.esmanager.config.YamlWriter;
import com.sphong.esmanager.domain.helm.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class HelmService {

    @Autowired
    private YamlWriter yamlWriter;
    @Autowired
    private CommandExecutor commandExecutor;

    public void writeESConfig(HelmValues helmValues) throws IOException {
        yamlWriter.writeYaml(helmValues);
    }

    public String createES() throws IOException, InterruptedException {
        return commandExecutor.run("helm install elasticsearch helm/elasticsearch/");
    }

    public String deleteES() throws IOException, InterruptedException {
        return commandExecutor.run("helm delete elasticsearch");
    }
}
