package com.sphong.esmanager.kubernetes.exception;

public class IllegalContainerStateException extends Exception {
    public IllegalContainerStateException(String message) {
        super(message);
    }
}
