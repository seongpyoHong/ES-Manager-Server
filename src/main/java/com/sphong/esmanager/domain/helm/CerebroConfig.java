package com.sphong.esmanager.domain.helm;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Getter
@NoArgsConstructor
public class CerebroConfig {
    private ImageConfig image;
    private String username;
    private String password;
    private Integer port;
    private Integer replicaCount;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CerebroConfig that = (CerebroConfig) o;
        return Objects.equals(image, that.image) &&
                Objects.equals(username, that.username) &&
                Objects.equals(password, that.password) &&
                Objects.equals(port, that.port) &&
                Objects.equals(replicaCount, that.replicaCount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(image, username, password, port, replicaCount);
    }
}
