package com.sphong.esmanager.kubernetes.dto.spec.probe;

import com.sphong.esmanager.kubernetes.exception.InvalidProbeTypeException;
import io.kubernetes.client.openapi.models.V1Probe;

public class ProbeFactory{
    public static Probe makeReadinessProbeType(V1Probe v1Probe) throws InvalidProbeTypeException {
        if (v1Probe.getHttpGet() != null) {
            return new ProbeHttpGet(v1Probe);
        } else if (v1Probe.getExec() != null) {
            return new ProbeExec(v1Probe);
        } else if (v1Probe.getTcpSocket() != null) {
            return new ProbeTcpSocket(v1Probe);
        }
        throw new InvalidProbeTypeException("Readiness Probe Type Not Supported!");
    }
}
