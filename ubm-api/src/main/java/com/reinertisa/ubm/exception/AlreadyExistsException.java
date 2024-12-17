package com.reinertisa.ubm.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@ResponseStatus(HttpStatus.CONFLICT)
public class AlreadyExistsException extends UbmException {
    private String fieldName;

    public AlreadyExistsException(String message) {
        super(message);
    }

    public AlreadyExistsException(String message, String fieldName) {
        super(message);
        this.fieldName = fieldName;
    }
}
