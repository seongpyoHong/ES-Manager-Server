package com.sphong.esmanager.domain.helm;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Getter
@NoArgsConstructor
public class ImageConfig {
    private String repository;
    private String tag;
    private String pullPolicy;

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
