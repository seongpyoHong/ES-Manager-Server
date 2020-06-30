package com.sphong.esmanager.domain;

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
}
