package com.sphong.esmanager.dto.kubernetes.spec.probe;

public class InvalidProbeTypeException extends Exception{
    public InvalidProbeTypeException(String message) {
        super(message);
    }
}
