package com.sphong.esmanager.terraform;

import com.sphong.esmanager.dto.ClusterRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.function.Consumer;

@Component
public class TerraformGenerator {

    private FileWriter fileWriter;

    public TerraformGenerator() throws IOException {
        String filename = "terraform/test-variable.tf";
        File file = new File(filename);
        fileWriter = new FileWriter(file, false);
    }

    public void writeVariableFile(List<TerraformVariable> variables) {
        variables.forEach(variable -> {
            try {
                fileWriter.write(TfFormatter.convertToTf(variable));
                fileWriter.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
