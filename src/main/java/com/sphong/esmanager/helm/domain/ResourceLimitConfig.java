package com.sphong.esmanager.helm.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ResourceLimitConfig {
    private String cpu;
    private String memory;
}
