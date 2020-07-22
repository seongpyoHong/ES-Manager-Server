package com.sphong.esmanager.users.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sphong.esmanager.cluster.repository.CredentialRepository;
import com.sphong.esmanager.cluster.domain.Credentials;
import com.sphong.esmanager.users.repository.UserRepository;
import com.sphong.esmanager.users.domain.Users;
import com.sphong.esmanager.users.dto.LoginRequestDto;
import com.sphong.esmanager.users.dto.UserRequestDto;
import com.sphong.esmanager.users.dto.UserResponseDto;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.io.IOException;
import java.util.Optional;

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

    public UserResponseDto saveUserInfos(UserRequestDto requestDto) throws IOException, ParseException {
        Credentials credentials = getCredential(requestDto.getProjectId());
        credentials.setId();

        Users savedUser = Optional.of(userRepository.save(Users.builder().credentials(credentials)
                                                                .dockerRegistry(requestDto.getDockerRegistry())
                                                                .email(requestDto.getEmail())
                                                                .password(requestDto.getPassword())
                                                                .projectName(requestDto.getProjectId())
                                                                .build()))
                                    .orElseThrow(() -> new IllegalArgumentException("Save Error!"));
        credentialRepository.save(credentials);

        return convertToDto(savedUser);
    }

    private UserResponseDto convertToDto(Users users) {
        return UserResponseDto.builder()
                            .email(users.getEmail())
                            .projectId(users.getProjectName())
                            .dockerRegistry(users.getDockerRegistry())
                            .build();
    }

    private Credentials getCredential(String projectId) throws IOException, ParseException {
        JSONObject jsonObject = (JSONObject) jsonParser.parse(new FileReader("credential/"+projectId+".json"));
        return jsonMapper.readValue(jsonObject.toJSONString(),Credentials.class);
    }

    public Users login(LoginRequestDto loginRequestDto) {
        return userRepository.findByEmail(loginRequestDto.getEmail())
                                                .orElseThrow(() -> new IllegalArgumentException("User Not Registered!"));
    }
}
