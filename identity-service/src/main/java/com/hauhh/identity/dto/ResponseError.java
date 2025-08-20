package com.hauhh.identity.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ResponseError {
    private int code;
    private String message;
}
