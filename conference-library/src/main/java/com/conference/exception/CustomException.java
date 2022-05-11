package com.conference.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CustomException extends RuntimeException {
    private final Integer status;

    public CustomException(String message, Integer code) {
        super(message);
        this.status = code;
    }

}