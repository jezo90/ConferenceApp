package com.conference.user.dao;

import com.conference.user.dto.UserChangeRequestDto;
import com.conference.user.dto.UserDto;
import com.conference.user.dto.UserRequestDto;
import com.conference.user.dto.UserResponseDto;

public class UserEntityMapper {

    public UserResponseDto map(UserEntity userEntity) {
        return new UserResponseDto(
                userEntity.getId(),
                userEntity.getUsername(),
                userEntity.getEmail()
        );
    }

    public UserEntity map(UserRequestDto userRequestDto) {
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(userRequestDto.username());
        userEntity.setEmail(userRequestDto.email());

        return userEntity;
    }

    public UserDto mapToDto(UserEntity userEntity)
    {
        return new UserDto(
                userEntity.getId(),
                userEntity.getUsername(),
                userEntity.getEmail()
        );
    }

    public UserEntity map(UserChangeRequestDto userChangeRequestDto) {
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(userChangeRequestDto.username());
        userEntity.setEmail(userChangeRequestDto.newEmail());

        return userEntity;
    }
}
