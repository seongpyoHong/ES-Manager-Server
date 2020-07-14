package com.sphong.esmanager.controller;

import com.sphong.esmanager.domain.users.Users;
import com.sphong.esmanager.dto.*;
import com.sphong.esmanager.service.ClusterService;
import com.sphong.esmanager.service.GoogleAuthService;
import com.sphong.esmanager.service.UserService;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

@RestController
public class HomeController {
    @Resource
    private SessionUsers sessionUsers;

    @Autowired
    private UserService userService;

    @Autowired
    private ClusterService clusterService;

    @Autowired
    private GoogleAuthService googleAuthService;

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

    @PostMapping("/set-cluster")
    public String setClusterConfig(@RequestBody ClusterRequestDto requestDto) throws IOException, InterruptedException {
        sessionUsers.setClusterName(requestDto.getClusterName());
        sessionUsers.setNodeRegion(requestDto.getNodeRegion());
        requestDto.setProjectId(sessionUsers.getProjectName());
        return clusterService.setClusterConfig(requestDto);
    }

    @PostMapping("/create-cluster")
    public String createCluster() throws IOException, InterruptedException {
        return clusterService.createCluster();
    }

    @PostMapping("/delete-cluster")
    public String deleteCluster() throws IOException, InterruptedException {
        return clusterService.deleteCluster();
    }

    @PostMapping("/get-auth")
    public String getAuth() throws IOException, InterruptedException {
        return googleAuthService.getGcloudAuth(sessionUsers.getProjectName(),
                                               sessionUsers.getClusterName(),
                                               sessionUsers.getNodeRegion());
    }
}
