package com.sphong.esmanager.dto;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import java.io.Serializable;

@Getter
@Setter
@Component
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
@ToString
public class SessionUsers implements Serializable {
    private static final long serialVersionUID = 1L;
    private String email;
    private String projectName;
    private String dockerRegistry;
    private String clusterName;
    private String nodeRegion;

    public SessionUsers() {
        this.email = "";
        this.projectName = "";
        this.dockerRegistry = "";
        this.clusterName = "";
        this.nodeRegion = "";
    }

    @Builder
    public SessionUsers(String email, String password, String projectName, String dockerRegistry) {
        this.email = email;
        this.projectName = projectName;
        this.dockerRegistry = dockerRegistry;
    }
}
