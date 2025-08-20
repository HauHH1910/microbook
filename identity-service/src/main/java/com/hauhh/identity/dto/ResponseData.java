package com.hauhh.identity.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseData<T> {
    @Builder.Default
    private int code = 200;

    private String message;

    private T result;

    public static <T> ResponseData<T> created(T data, String message) {
        return ResponseData.<T>builder()
                .message(message)
                .result(data)
                .build();
    }

    public static ResponseData<Void> noContent(String message) {
        return ResponseData.<Void>builder()
                .code(HttpStatus.NO_CONTENT.value())
                .message(message)
                .build();
    }

    public static <T> ResponseData<T> ok(T data, String message) {
        return ResponseData.<T>builder()
                .code(HttpStatus.OK.value())
                .message(message)
                .result(data)
                .build();
    }
}
