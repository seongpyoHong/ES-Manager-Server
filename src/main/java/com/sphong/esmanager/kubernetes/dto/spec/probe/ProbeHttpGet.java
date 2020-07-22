package com.sphong.esmanager.kubernetes.dto.spec.probe;

import io.kubernetes.client.openapi.models.V1Probe;
import lombok.Getter;

@Getter
public class ProbeHttpGet extends Probe {
    private String path;
    private String port;
    public ProbeHttpGet(V1Probe v1Probe) {
        super(v1Probe);
        this.type = ProbeType.HTTPGET.getType();
        this.port = v1Probe.getHttpGet().getPort().toString();
        this.path = v1Probe.getHttpGet().getPath();
    }
}
