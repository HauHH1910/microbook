package com.hauhh.profile.exception;

import lombok.Getter;

@Getter
public class ProfileServiceException extends RuntimeException {
    private final ErrorConstant errorConstant;

    public ProfileServiceException(final ErrorConstant errorConstant) {
        super(errorConstant.getMessage());
        this.errorConstant = errorConstant;
    }

    @Override
    public String toString() {
        return String.format
                (
                        "Profile Exception: [code - %d ] - [message: %s ] - [status code: %s ]",
                        errorConstant.getCode(),
                        errorConstant.getMessage(),
                        errorConstant.getStatusCode()
                );
    }
}
