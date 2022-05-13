package com.conference.user.mapper;

import com.conference.user.dto.UserChangeRequestDto;
import com.conference.user.dto.UserRequestDto;
import com.conference.user.dto.UserResponseDto;
import com.conference.user.model.UserChangeRequest;
import com.conference.user.model.UserRequest;
import com.conference.user.model.UserResponse;

public class UserMapper {
    public UserRequestDto map(UserRequest userRequest)
    {
        return new UserRequestDto(
                userRequest.username(),
                userRequest.email());
    }

    public UserResponse map(UserResponseDto userResponseDto)
    {
        return new UserResponse(
                userResponseDto.username(),
                userResponseDto.email()
        );
    }

    public UserChangeRequestDto map(UserChangeRequest userChangeRequest)
    {
        return new UserChangeRequestDto(
                userChangeRequest.username(),
                userChangeRequest.currentEmail(),
                userChangeRequest.newEmail()
        );
    }
}
