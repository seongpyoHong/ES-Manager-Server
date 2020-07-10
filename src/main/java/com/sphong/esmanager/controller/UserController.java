package com.sphong.esmanager.controller;

import com.sphong.esmanager.domain.users.Users;
import com.sphong.esmanager.dto.LoginRequestDto;
import com.sphong.esmanager.dto.SessionUsers;
import com.sphong.esmanager.dto.UserRequestDto;
import com.sphong.esmanager.dto.UserResponseDto;
import com.sphong.esmanager.service.UserService;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.IOException;

@RestController
public class UserController {
    @Resource
    private SessionUsers sessionUsers;

    @Autowired
    private UserService userService;

    @PostMapping("/signup")
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
