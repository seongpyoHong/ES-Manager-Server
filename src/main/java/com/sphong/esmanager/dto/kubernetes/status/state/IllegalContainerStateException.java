package com.sphong.esmanager.dto.kubernetes.status.state;

public class IllegalContainerStateException extends Exception {
    public IllegalContainerStateException(String message) {
        super(message);
    }
}
