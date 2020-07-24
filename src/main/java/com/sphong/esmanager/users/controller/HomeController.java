package com.sphong.esmanager.users.controller;

import com.sphong.esmanager.users.domain.Users;
import com.sphong.esmanager.users.dto.LoginRequestDto;
import com.sphong.esmanager.common.SessionUsers;
import com.sphong.esmanager.users.dto.UserRequestDto;
import com.sphong.esmanager.users.dto.UserResponseDto;
import com.sphong.esmanager.users.service.UserService;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.IOException;

@RestController
public class HomeController {
    @Resource
    private SessionUsers sessionUsers;

    @Autowired
    private UserService userService;

    @PostMapping("/user")
    public UserResponseDto signUp(@RequestBody UserRequestDto requestDto) throws IOException, ParseException {
        return userService.saveUserInfos(requestDto);
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginRequestDto loginRequestDto) {
        if (!sessionUsers.getEmail().equals(loginRequestDto.getEmail())) {
            Users user = userService.login(loginRequestDto);
            sessionUsers = SessionUsers.builder()
                                        .email(user.getEmail())
                                        .dockerRegistry(user.getDockerRegistry())
                                        .projectName(user.getProjectName())
                                        .build();
        }
        return sessionUsers.getEmail();
    }
}
