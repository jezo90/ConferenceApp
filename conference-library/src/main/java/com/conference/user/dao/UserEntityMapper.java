package com.conference.user.dao;

import com.conference.user.dto.UserChangeRequestDto;
import com.conference.user.dto.UserDto;
import com.conference.user.dto.UserRequestDto;
import com.conference.user.dto.UserResponseDto;

public class UserEntityMapper {

    public static UserResponseDto map(UserEntity userEntity) {
        return new UserResponseDto(
                userEntity.getId(),
                userEntity.getUsername(),
                userEntity.getEmail()
        );
    }

    public static UserEntity map(UserRequestDto userRequestDto) {
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(userRequestDto.username());
        userEntity.setEmail(userRequestDto.email());

        return userEntity;
    }

    public static UserDto mapToDto(UserEntity userEntity)
    {
        return new UserDto(
                userEntity.getId(),
                userEntity.getUsername(),
                userEntity.getEmail()
        );
    }

    public static UserEntity map(UserChangeRequestDto userChangeRequestDto) {
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(userChangeRequestDto.username());
        userEntity.setEmail(userChangeRequestDto.newEmail());

        return userEntity;
    }
}
