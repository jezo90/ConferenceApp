package com.conference.user.dto;

public record UserChangeRequestDto(
        String username,
        String currentEmail,
        String newEmail
) {
}
