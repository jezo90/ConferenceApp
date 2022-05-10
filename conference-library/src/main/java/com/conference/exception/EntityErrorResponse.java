package com.conference.exception;

import java.time.LocalDateTime;

public record EntityErrorResponse(
        Integer status,
        String message,
        LocalDateTime timestamp
) {
}