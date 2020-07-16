package com.sphong.esmanager;

import com.sphong.esmanager.config.CommandExecutor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class CommandExecutorTest {
    @Autowired
    private CommandExecutor commandExecutor;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd-HH");
    @Test
    void commandExecuteTest() throws IOException, InterruptedException {
        Date currentTime = new Date();
        String currentTimeStr = dateFormat.format(currentTime);
        assertEquals(currentTimeStr,commandExecutor.run("date +%Y%m%d-%H").trim());
    }
}
