package com.conference.user.model;

public record UserResponse(
        Long id,
        String username,
        String email) {
}
