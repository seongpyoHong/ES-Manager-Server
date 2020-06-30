package com.sphong.esmanager.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ImageConfig {
    private String repository;
    private String tag;
    private String pullPolicy;

    @Builder
    public ImageConfig(String repository, String tag, String pullPolicy) {
        this.repository = repository;
        this.tag = tag;
        this.pullPolicy = pullPolicy;
    }
}
