package com.sphong.esmanager.service;

import com.sphong.esmanager.utils.CommandExecutor;
import com.sphong.esmanager.dto.ClusterRequestDto;
import com.sphong.esmanager.terraform.TerraformGenerator;
import com.sphong.esmanager.terraform.TerraformVariable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ClusterService {
    @Autowired
    private TerraformGenerator terraformGenerator;

    @Autowired
    private CommandExecutor commandExecutor;

    public String setClusterConfig(ClusterRequestDto requestDto) throws IOException, InterruptedException {
        terraformGenerator.writeVariableFile(convertToTerraforVariable(requestDto));
        return commandExecutor.run("terraform init terraform/");
    }

    private List<TerraformVariable> convertToTerraforVariable(ClusterRequestDto requestDto) {
        Map<String, String> members = requestDto.getMembers();
        return members.keySet().stream().map(k -> TerraformVariable.builder().name(k).type("string").defaultValue(members.get(k)).build()).collect(Collectors.toList());
    }

    public String createCluster() throws IOException, InterruptedException {
        return commandExecutor.run("terraform apply -auto-approve terraform/");
    }

    public String deleteCluster() throws IOException, InterruptedException {
        return commandExecutor.run("terraform destroy -auto-approve terraform/");
    }
}
