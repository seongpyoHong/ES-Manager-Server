package com.sphong.esmanager.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CerebroConfig {
    private ImageConfig image;
    private String userName;
    private String password;
    private Integer port;
    private Integer replicaCount;
    @Builder
    public CerebroConfig(ImageConfig image, String username, String password, Integer port, Integer replicaCount) {
        this.image = image;
        this.userName = username;
        this.password = password;
        this.port = port;
        this.replicaCount = replicaCount;
    }
}
