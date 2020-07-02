package com.sphong.esmanager.domain;

import java.util.function.Function;

public enum ImageType {
    ELASTICSEARCH("elasticsearch-base", "6.8.2"), KIBANA("kibana", "6.8.2"),CEREBRO("cerebro","0.9.0");
    private String name;
    private String tag;

    ImageType(String name, String tag) {
        this.name = name;
        this.tag = tag;
    }

    public String getName() {
        return name;
    }

    public String getTag() {
        return tag;
    }
}
