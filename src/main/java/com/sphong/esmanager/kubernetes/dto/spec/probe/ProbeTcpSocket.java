package com.sphong.esmanager.kubernetes.dto.spec.probe;

import io.kubernetes.client.openapi.models.V1Probe;
import lombok.Getter;

@Getter
public class ProbeTcpSocket extends Probe {
    private String port;
    public ProbeTcpSocket(V1Probe v1Probe) {
        super(v1Probe);
        this.type = ProbeType.TCPSOCKET.getType();
        this.port = v1Probe.getTcpSocket().getPort().toString();
    }
}
