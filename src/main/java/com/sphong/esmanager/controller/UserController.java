package com.sphong.esmanager.controller;

import com.sphong.esmanager.dto.UserRequestDto;
import com.sphong.esmanager.service.UserService;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/signup")
    public String signUp(@RequestBody UserRequestDto requestDto) throws IOException, ParseException {
        return userService.setUserInfos(requestDto);
    }

    //Login
}
