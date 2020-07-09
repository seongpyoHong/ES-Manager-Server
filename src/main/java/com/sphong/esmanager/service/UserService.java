package com.sphong.esmanager.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sphong.esmanager.domain.users.CredentialRepository;
import com.sphong.esmanager.domain.users.Credentials;
import com.sphong.esmanager.domain.users.UserRepository;
import com.sphong.esmanager.domain.users.Users;
import com.sphong.esmanager.dto.LoginRequestDto;
import com.sphong.esmanager.dto.UserRequestDto;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CredentialRepository credentialRepository;

    @Qualifier("jsonMapper")
    @Autowired
    private ObjectMapper jsonMapper;

    @Autowired
    private JSONParser jsonParser;

    public String setUserInfos(UserRequestDto requestDto) throws IOException, ParseException {
        Credentials credentials = getCredential(requestDto.getProjectId());
        credentials.setId();

        userRepository.save(Users.builder().credentials(credentials)
                                            .dockerRegistry(requestDto.getDockerRegistry())
                                            .email(requestDto.getEmail())
                                            .password(requestDto.getPassword())
                                            .projectName(requestDto.getProjectId())
                                    .build());
        credentialRepository.save(credentials);

        return userRepository.findAll().get(0).toString();
    }

    private Credentials getCredential(String projectId) throws IOException, ParseException {
        JSONObject jsonObject = (JSONObject) jsonParser.parse(new FileReader("credential/"+projectId+".json"));
        return jsonMapper.readValue(jsonObject.toJSONString(),Credentials.class);
    }

    public Users login(LoginRequestDto loginRequestDto) {
        return userRepository.findByEmail(loginRequestDto.getEmail())
                                                .orElseThrow(() -> new IllegalArgumentException("가입되지 않은 사용자입니다."));
    }
}
