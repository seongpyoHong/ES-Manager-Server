package com.sphong.esmanager.common;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sphong.esmanager.helm.domain.CerebroConfig;
import com.sphong.esmanager.helm.domain.ImageConfig;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class YamlMapperTest {
    private static final Integer PORT = 8080;
    private static final Integer REPLCIA_COUNT = 1;
    @Autowired
    @Qualifier("yamlMapper")
    private ObjectMapper objectMapper;
    private ImageConfig imageConfig;
    private CerebroConfig cerebroConfig;
    public YamlMapperTest() {
        imageConfig = ImageConfig.builder()
                                    .pullPolicy("pullPolicy")
                                    .repository("repository")
                                    .tag("tag")
                                  .build();
        cerebroConfig = CerebroConfig.builder()
                                        .image(imageConfig)
                                        .username("username")
                                        .password("password")
                                        .port(PORT)
                                        .replicaCount(REPLCIA_COUNT)
                                      .build();
    }

    @Test
    void yamlSerializeSimpleClassTest() throws JsonProcessingException {
        String imageYamlStr = "repository: \"repository\"\ntag: \"tag\"\npullPolicy: \"pullPolicy\"";
        ImageConfig result = objectMapper.readValue(imageYamlStr, ImageConfig.class);
        assertEquals(result, imageConfig);
    }

    @Test
    void yamlSerializeNestedClassTest() throws JsonProcessingException {
        String cerebroYamlStr = "image:\n    repository: \"repository\"\n    tag: \"tag\"\n    pullPolicy: \"pullPolicy\"\nusername: \"username\"\npassword: \"password\"\nport: 8080\nreplicaCount: 1";
        CerebroConfig result = objectMapper.readValue(cerebroYamlStr,CerebroConfig.class);
        assertEquals(result, cerebroConfig);
    }
}
