package com.hauhh.profile.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ResponseError {
    private int code;
    private String message;
}
