package com.sphong.esmanager.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.fasterxml.jackson.dataformat.yaml.YAMLGenerator;
import org.json.simple.parser.JSONParser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class MapperConfig {
    @Bean(name = "yamlMapper")
    public ObjectMapper yamlMapper() {
        return new ObjectMapper(new YAMLFactory().disable(YAMLGenerator.Feature.WRITE_DOC_START_MARKER));
    }

    @Bean(name = "jsonMapper")
    @Primary
    public ObjectMapper jsonMapper() {
        return new ObjectMapper();
    }

    @Bean
    public JSONParser jsonParser() {
        return new JSONParser();
    }
}
