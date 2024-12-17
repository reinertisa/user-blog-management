package com.reinertisa.ubm.exception;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.annotation.Nullable;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorData> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        Map<String, List<ErrorMessage>> data = new HashMap<>();

        ex.getBindingResult().getFieldErrors().forEach(error -> {
            String fieldName = error.getField();
            String message = error.getDefaultMessage();
            data.computeIfAbsent(fieldName, k -> new ArrayList<>()).add(new ErrorMessage(null, message, null));
        });

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorData(data));
    }

    @ExceptionHandler(UbmException.class)
    public ResponseEntity<Object> handleCtsException(UbmException ex) {
        Map<String, List<ErrorMessage>> data = new HashMap<>();

        if (ex.getCause() instanceof AlreadyExistsException baseEx) {
            String fieldName = baseEx.getFieldName();
            String message = baseEx.getMessage();
            if (fieldName != null) {
                data.computeIfAbsent(fieldName, k -> new ArrayList<>()).add(new ErrorMessage(null, message, null));
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorData(data));
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
        } else if (ex.getCause() instanceof ResourceNotFoundException baseEx) {
            String fieldName = baseEx.getFieldName();
            String message = baseEx.getMessage();
            if (fieldName != null) {
                data.computeIfAbsent(fieldName, k -> new ArrayList<>()).add(new ErrorMessage(null, message, null));
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorData(data));
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
        }
        return ResponseEntity.internalServerError().build();
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException ex) {
        String message = ex.getMessage();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<String> handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
        if (ex.getCause() instanceof org.hibernate.exception.ConstraintViolationException) {
            org.hibernate.exception.ConstraintViolationException cause = (org.hibernate.exception.ConstraintViolationException) ex.getCause();
            if (cause.getSQLException().getSQLState().equals("23505")) { // PostgreSQL unique constraint violation
                return new ResponseEntity<>("Duplicate key value violates unique constraint.", HttpStatus.BAD_REQUEST);
            }
        }
        return new ResponseEntity<>("Error processing request.", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public record ErrorData(Map<String, List<ErrorMessage>> data) { }


    public static class ErrorMessage implements Comparable<ErrorMessage> {

        private final String m_code;
        private final String m_message;
        private final String m_stacktrace;
        private String m_objectType;
        private String m_objectId;

        @JsonCreator
        public ErrorMessage(@JsonProperty("code") @Nullable String code,
                            @JsonProperty("objectType") @Nullable String objectType,
                            @JsonProperty("objectId") @Nullable String objectId,
                            @JsonProperty("message") @Nullable String message,
                            @JsonProperty("stacktrace") @Nullable String stacktrace) {
            m_code = code;
            m_objectType = objectType;
            m_objectId = objectId;
            m_message = message;
            m_stacktrace = stacktrace;
        }

        public ErrorMessage(@JsonProperty("code") @Nullable String code,
                            @JsonProperty("message") @Nullable String message,
                            @JsonProperty("stacktrace") @Nullable String stacktrace) {
            m_code = code;
            m_message = message;
            m_stacktrace = stacktrace;
        }
        public @Nullable String getCode() {
            return m_code;
        }

        public @Nullable String getMessage() {
            return m_message;
        }

        public @Nullable String getStacktrace() {
            return m_stacktrace;
        }

        public @Nullable String getObjectType() {
            return m_objectType;
        }

        public @Nullable String getObjectId() {
            return m_objectId;
        }



        @Override
        public int compareTo(ErrorMessage o) {
            return 0;
        }

        @Override
        public String toString() {
            StringBuilder builder = new StringBuilder();
            if (m_code != null) {
                builder.append("[")
                        .append(m_code)
                        .append("]");
            }
            if (m_objectType != null) {
                builder.append("[")
                        .append(m_objectType)
                        .append(":")
                        .append(m_objectId)
                        .append("]");
            } else if (m_objectId != null) {
                builder.append("[")
                        .append(m_objectId)
                        .append("]");
            }
            if (m_message != null) {
                if (!builder.isEmpty()) {
                    builder.append(" ");
                }
                builder.append(m_message);
            }
            return builder.toString();
        }
    }

}
