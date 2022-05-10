package com.conference.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class EntityNotFoundException extends RuntimeException {
    private final Integer status;

    public EntityNotFoundException(String message) {
        super(message);
        this.status = 404;
    }

    public EntityNotFoundException(String message, Integer code) {
        super(message);
        this.status = code;
    }

}