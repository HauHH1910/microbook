package com.hauhh.profile.dto;

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
public class BasedResponse<T> {
    private int code;

    private String message;

    private T result;

    public static <T> BasedResponse<T> created(T data, String message) {
        return BasedResponse.<T>builder()
                .code(HttpStatus.CREATED.value())
                .message(message)
                .result(data)
                .build();
    }

    public static BasedResponse<Void> noContent(String message) {
        return BasedResponse.<Void>builder()
                .code(HttpStatus.NO_CONTENT.value())
                .message(message)
                .build();
    }

    public static <T> BasedResponse<T> ok(T data, String message) {
        return BasedResponse.<T>builder()
                .code(HttpStatus.OK.value())
                .message(message)
                .result(data)
                .build();
    }
}
