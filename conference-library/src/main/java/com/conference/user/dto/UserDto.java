package com.conference.user.dto;

public record UserDto(
        Long id,
        String username,
        String email
) {
}