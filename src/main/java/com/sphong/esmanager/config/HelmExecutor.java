package com.sphong.esmanager.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Component
public class HelmExecutor {
    public String run(String cmd) throws IOException {
        Process process = null;
        Runtime runtime = Runtime.getRuntime();
        StringBuffer successOutput = new StringBuffer();
        StringBuffer errorOutput = new StringBuffer();
        BufferedReader successBufferReader = null;
        BufferedReader errorBufferReader = null;
        String msg = null;

        List<String> cmdList = new ArrayList<String>();

        cmdList.add("/bin/sh");
        cmdList.add("-c");
        cmdList.add(cmd);

        String[] array = cmdList.toArray(new String[cmdList.size()]);

        try {
            // 명령어 실행
            process = runtime.exec(array);
            // shell 실행이 정상 동작했을 경우
            successBufferReader = new BufferedReader(new InputStreamReader(process.getInputStream(), "EUC-KR"));
            while ((msg = successBufferReader.readLine()) != null) {
                successOutput.append(msg).append(System.getProperty("line.separator"));
            }

            // shell 실행시 에러가 발생했을 경우
            errorBufferReader = new BufferedReader(new InputStreamReader(process.getErrorStream(), "EUC-KR"));
            while ((msg = errorBufferReader.readLine()) != null) {
                errorOutput.append(msg).append(System.getProperty("line.separator"));
            }

            process.waitFor();

            if (process.exitValue() == 0) {
                System.out.println("Success!");
                System.out.println(successOutput.toString());
            } else {
                System.out.println("Failed!");
                System.out.println(errorOutput.toString());
            }


        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        } finally {
            try {
                process.destroy();
                if (successBufferReader != null) successBufferReader.close();
                if (errorBufferReader != null) errorBufferReader.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
        return successOutput.toString();
    }
}
