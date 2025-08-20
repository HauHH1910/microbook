package com.hauhh.identity.exception;

import lombok.Getter;

@Getter
public class IdentityServiceException extends RuntimeException {
    private final ErrorConstant errorConstant;

    public IdentityServiceException(final ErrorConstant errorConstant) {
        super(errorConstant.getMessage());
        this.errorConstant = errorConstant;
    }

    @Override
    public String toString() {
        return String.format
                (
                        "IdentityService Exception: [code - %d ] - [message: %s ] - [status code: %s ]",
                        errorConstant.getCode(),
                        errorConstant.getMessage(),
                        errorConstant.getStatusCode()
                );
    }
}
