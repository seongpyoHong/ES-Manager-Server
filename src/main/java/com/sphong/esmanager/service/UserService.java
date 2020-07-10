package com.sphong.esmanager.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sphong.esmanager.domain.users.CredentialRepository;
import com.sphong.esmanager.domain.users.Credentials;
import com.sphong.esmanager.domain.users.UserRepository;
import com.sphong.esmanager.domain.users.Users;
import com.sphong.esmanager.dto.LoginRequestDto;
import com.sphong.esmanager.dto.UserRequestDto;
import com.sphong.esmanager.dto.UserResponseDto;
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
//        Credentials credentials = getCredential(requestDto.getProjectId());
        Credentials credentials = getDefaultCredential();
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
    private Credentials getDefaultCredential() {
        return Credentials.builder().authProviderCertUrl("1").authUri("1").clientCertUrl("1").clientEmail("1").clientId("1").privateKey("1").privateKeyId("1").projectId("1").tokenUri("1").type("1").build();
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
