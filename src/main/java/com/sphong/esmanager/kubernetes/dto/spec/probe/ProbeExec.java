package com.sphong.esmanager.kubernetes.dto.spec.probe;

import io.kubernetes.client.openapi.models.V1Probe;
import lombok.Getter;

@Getter
public class ProbeExec extends Probe {

    private String command;
    public ProbeExec(V1Probe v1Probe) {
        super(v1Probe);
        this.type = ProbeType.EXEC.getType();
        this.command = v1Probe.getExec().getCommand().toString();
    }
}
