package com.sphong.esmanager.terraform.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
@Getter
public class TerraformVariable {
    private String name;
    private String type;
    private String defaultValue;

    @Builder
    public TerraformVariable(String name, String type, String defaultValue) {
        this.name = name;
        this.type = type;
        this.defaultValue = defaultValue;
    }
}
