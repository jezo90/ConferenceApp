package com.conference.user.port.outbound;


import com.conference.user.dto.UserChangeRequestDto;
import com.conference.user.dto.UserDto;
import com.conference.user.dto.UserRequestDto;
import com.conference.user.dto.UserResponseDto;

import java.util.Optional;

public interface UserRepository {
    UserResponseDto saveUser(UserRequestDto userRequestDto);
    Optional<UserDto> findByUsername(String username);
    UserResponseDto changeNickname(UserChangeRequestDto userChangeRequestDto);
}