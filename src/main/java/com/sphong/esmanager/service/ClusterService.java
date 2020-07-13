package com.sphong.esmanager.service;

import com.sphong.esmanager.dto.ClusterRequestDto;
import com.sphong.esmanager.terraform.TerraformGenerator;
import com.sphong.esmanager.terraform.TerraformVariable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class ClusterService {
    @Autowired
    private TerraformGenerator terraformGenerator;

    public void createCluster(ClusterRequestDto requestDto) {
        terraformGenerator.writeVariableFile(convertToTerraforVariable(requestDto));
    }

    private List<TerraformVariable> convertToTerraforVariable(ClusterRequestDto requestDto) {
        Map<String, String> members = requestDto.getMembers();
        return members.keySet().stream().map(k -> TerraformVariable.builder().name(k).type("string").defaultValue(members.get(k)).build()).collect(Collectors.toList());
    }
}
