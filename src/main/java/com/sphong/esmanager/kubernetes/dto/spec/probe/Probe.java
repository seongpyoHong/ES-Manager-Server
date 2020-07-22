package com.sphong.esmanager.kubernetes.dto.spec.probe;

import io.kubernetes.client.openapi.models.V1Probe;

public abstract class Probe {
    protected V1Probe v1Probe;
    public String type;
    public Probe(V1Probe v1Probe) {
        this.v1Probe = v1Probe;
    }
}
