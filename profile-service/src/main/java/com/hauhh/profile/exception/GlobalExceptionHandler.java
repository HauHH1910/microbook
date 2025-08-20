package com.hauhh.profile.exception;


import com.hauhh.profile.dto.ResponseError;
import jakarta.validation.ConstraintViolation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.nio.file.AccessDeniedException;
import java.util.Map;
import java.util.Objects;

@RestControllerAdvice
@Slf4j(topic = "Global Exception Handler")
public class GlobalExceptionHandler {

    private static final String MIN_ATTRIBUTE = "min";
    private static final String VALUE_ATTRIBUTE = "value";
    private static final int FIRST_INDEX = 0;

    @ExceptionHandler(value = Exception.class)
    ResponseEntity<ResponseError> handlingRuntimeException(RuntimeException exception) {
        log.error("Exception: {}", exception.getMessage(), exception);
        return responseErrorMapping(ErrorConstant.UNCATEGORIZED_EXCEPTION, null);
    }


    @ExceptionHandler(value = ProfileServiceException.class)
    ResponseEntity<ResponseError> handlingAppException(ProfileServiceException exception) {
        log.error("IdentityServiceException: {}", exception.getMessage(), exception);
        return responseErrorMapping(exception.getErrorConstant(), null);
    }

    @ExceptionHandler(value = AccessDeniedException.class)
    ResponseEntity<ResponseError> handlingAccessDeniedException(AccessDeniedException exception) {
        log.error("AccessDeniedException: {}", exception.getMessage(), exception);
        return responseErrorMapping(ErrorConstant.UNAUTHORIZED, null);
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseError> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        Map<String, Object> attributes = null;

        String enumKey = Objects.requireNonNull(ex.getFieldError()).getDefaultMessage();

        ErrorConstant errorConstant;
        try {
            errorConstant = ErrorConstant.valueOf(enumKey);

            ConstraintViolation<?> constraintViolation = ex.getBindingResult().getAllErrors()
                    .get(FIRST_INDEX)
                    .unwrap(ConstraintViolation.class);

            attributes = constraintViolation.getConstraintDescriptor().getAttributes();

            log.error("constraint violation: {}, exception:", attributes, ex);
        } catch (IllegalArgumentException exception) {
            log.error("invalid enum key: {}, exception: ", enumKey, ex);
            errorConstant = ErrorConstant.INVALID_KEY;
        }
        return responseErrorMapping(errorConstant, attributes);
    }


    private String mapAttribute(String message, Map<String, Object> attributes) {
        if (attributes.containsKey(VALUE_ATTRIBUTE)) {
            return message.replace("{" + VALUE_ATTRIBUTE + "}", String.valueOf(attributes.get(VALUE_ATTRIBUTE)));
        }
        if (attributes.containsKey(MIN_ATTRIBUTE)) {
            return message.replace("{" + MIN_ATTRIBUTE + "}", String.valueOf(attributes.get(MIN_ATTRIBUTE)));
        }
        return message;
    }

    private ResponseEntity<ResponseError> responseErrorMapping(ErrorConstant errorConstant, Map<String, Object> attributes) {
        String message = errorConstant.getMessage();
        if (attributes != null) {
            message = mapAttribute(message, attributes);
        }
        return ResponseEntity.status(errorConstant.getStatusCode())
                .body(ResponseError.builder()
                        .code(errorConstant.getCode())
                        .message(message)
                        .build());
    }
}
