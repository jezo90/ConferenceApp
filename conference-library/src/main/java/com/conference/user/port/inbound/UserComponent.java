package com.conference.user.port.inbound;

import com.conference.user.dto.UserChangeRequestDto;
import com.conference.user.dto.UserRequestDto;
import com.conference.user.dto.UserResponseDto;

import java.util.List;

public interface UserComponent {
    UserResponseDto saveUser(UserRequestDto userRequestDto);
    UserResponseDto changeEmail(UserChangeRequestDto userChangeRequestDto);
    List<UserResponseDto> getAllUsers();
}
