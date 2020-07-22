package com.sphong.esmanager.common;

import org.springframework.stereotype.Component;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class CommandExecutor {
    public String run(String cmd) throws IOException, InterruptedException {
        String[] command = createCommand(cmd);
        String result = null;
        try {
            result =  executeCommand(command);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    private String[] createCommand(String cmd) {
        List<String> cmdList = Arrays.asList("/bin/sh", "-c", cmd);
        return cmdList.toArray(new String[cmdList.size()]);
    }

    private String executeCommand(String[] command) throws IOException, InterruptedException {
        Process process = Runtime.getRuntime().exec(command);
        process.waitFor();
        
        String output = null;
        if (process.exitValue() == 0) {
            output = getSuccessOutput(process);
        } else {
            output = getErrorOutput(process);
        }
        process.destroy();

        return output;
    }

    private String getSuccessOutput(Process process) throws IOException {
        BufferedReader successBufferReader = new BufferedReader(new InputStreamReader(process.getInputStream(), "EUC-KR"));
        return getStream(successBufferReader);
    }

    private String getErrorOutput(Process process) throws IOException {
        BufferedReader errorBufferReader = new BufferedReader(new InputStreamReader(process.getErrorStream(), "EUC-KR"));
        return getStream(errorBufferReader);
    }

    private String getStream(BufferedReader bufferedReader) throws IOException {
        String msg = null;
        StringBuilder output = new StringBuilder();
        while((msg = bufferedReader.readLine()) != null) {
            output.append(msg).append(System.getProperty("line.separator"));
        }

        bufferedReader.close();

        return output.toString();
    }
}
