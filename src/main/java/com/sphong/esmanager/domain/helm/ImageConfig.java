package com.sphong.esmanager.domain.helm;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;

import static com.sphong.esmanager.domain.helm.ImageType.*;

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

    public static ImageConfig getElasticsearchImage() {
        return ImageConfig.builder()
                        .pullPolicy("IfNotPresent")
                        .repository(ELASTICSEARCH.getName())
                        .tag(ELASTICSEARCH.getTag())
                        .build();
    }

    public static ImageConfig getCerebroImage() {
        return ImageConfig.builder()
                .pullPolicy("IfNotPresent")
                .repository(CEREBRO.getName())
                .tag(CEREBRO.getTag())
                .build();
    }

    public static ImageConfig getKibanaImage() {
        return ImageConfig.builder()
                .pullPolicy("IfNotPresent")
                .repository(KIBANA.getName())
                .tag(KIBANA.getTag())
                .build();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ImageConfig that = (ImageConfig) o;
        return Objects.equals(repository, that.repository) &&
                Objects.equals(tag, that.tag) &&
                Objects.equals(pullPolicy, that.pullPolicy);
    }

    @Override
    public int hashCode() {
        return Objects.hash(repository, tag, pullPolicy);
    }
}
