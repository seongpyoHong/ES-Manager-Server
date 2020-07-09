package com.sphong.esmanager.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
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

    @Builder
    public SessionUsers(String email, String password, String projectName, String dockerRegistry) {
        this.email = email;
        this.projectName = projectName;
        this.dockerRegistry = dockerRegistry;
    }
}
