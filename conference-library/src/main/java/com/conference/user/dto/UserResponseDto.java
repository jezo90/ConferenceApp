package com.conference.user.dto;

public record UserResponseDto(
        Long id,
        String username,
        String email) {
}
