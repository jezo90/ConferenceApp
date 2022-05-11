package com.conference.user.dto;

public record UserDto(
        Long id,
        String username,
        String email
) {
    public UserDto setEmail(String newEmail)
    {
        return new UserDto(id, username, newEmail);
    }
}