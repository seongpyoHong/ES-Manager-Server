package com.sphong.esmanager.domain.helm;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CerebroConfig {
    private ImageConfig image;
    private String username;
    private String password;
    private Integer port;
    private Integer replicaCount;
    @Builder
    public CerebroConfig(ImageConfig image, String username, String password, Integer port, Integer replicaCount) {
        this.image = image;
        this.username = username;
        this.password = password;
        this.port = port;
        this.replicaCount = replicaCount;
    }
}
