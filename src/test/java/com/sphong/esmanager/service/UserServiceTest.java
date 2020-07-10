package com.sphong.esmanager.service;

import com.sphong.esmanager.domain.users.UserRepository;
import com.sphong.esmanager.domain.users.Users;
import com.sphong.esmanager.dto.LoginRequestDto;
import com.sphong.esmanager.dto.UserRequestDto;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class UserServiceTest {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @AfterEach
    public void tearDown() {
        userRepository.deleteAll();
    }
    @Test
    public void signUpTest() throws IOException, ParseException {
        //given
        UserRequestDto requestDto = UserRequestDto.builder()
                                                .email("sphong@email.com")
                                                .dockerRegistry("docker_registry")
                                                .projectId("project_name")
                                                .password("password")
                                                .build();

        //when
        userService.saveUserInfos(requestDto);
        //then
        Users savedUser = userRepository.findByEmail("sphong@email.com").get();
        assertEquals(savedUser.getDockerRegistry(), "docker_registry");
        assertEquals(savedUser.getProjectName(), "project_name");
        assertEquals(savedUser.getPassword(), "password");
    }
    //first login
    @Test
    public void firstLoginTest() throws IOException, ParseException {
        //given
        UserRequestDto requestDto = UserRequestDto.builder()
                .email("sphong@email.com")
                .dockerRegistry("docker_registry")
                .projectId("project_name")
                .password("password")
                .build();
        userService.saveUserInfos(requestDto);

        LoginRequestDto loginRequestDto = new LoginRequestDto("sphong@email.com", "password");
        //when
        Users foundUser = userService.login(loginRequestDto);
        //then
        assertEquals("sphong@email.com", foundUser.getEmail());
        assertEquals("docker_registry", foundUser.getDockerRegistry());
        assertEquals("project_name", foundUser.getProjectName());
        assertEquals("password", foundUser.getPassword());
    }
}