package com.sphong.esmanager.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class KibanaConfig {
    private ImageConfig image;
    @Builder
    public KibanaConfig(ImageConfig image) {
        this.image = image;
    }
}
