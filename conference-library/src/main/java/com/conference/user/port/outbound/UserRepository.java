package com.conference.user.port.outbound;


import com.conference.user.dto.UserDto;
import com.conference.user.dto.UserRequestDto;
import com.conference.user.dto.UserResponseDto;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    UserResponseDto saveUser(UserRequestDto userRequestDto);

    Optional<UserDto> findByUsername(String username);

    UserResponseDto changeEmail(UserDto userDto);

    Optional<Long> findIdByUsernameAndEmail(String username, String email);

    List<UserResponseDto> getAllUsers();
}