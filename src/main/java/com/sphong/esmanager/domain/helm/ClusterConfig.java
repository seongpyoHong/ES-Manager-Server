package com.sphong.esmanager.domain.helm;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ClusterConfig {
    private String userEmail;
    private String managerEmail;
    @Builder
    public ClusterConfig(String userEmail, String managerEmail) {
        this.userEmail = userEmail;
        this.managerEmail = managerEmail;
    }
}
