package com.sphong.esmanager.kubernetes.dto.spec.probe;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum ProbeType {
    HTTPGET("HttpGet"), EXEC("Exec"), TCPSOCKET("TCPSocket");
    private final String type;
}
