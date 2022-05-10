package com.conference.user.model;

public record UserChangeRequest(
        String username,
        String currentEmail,
        String newEmail
) {
}
