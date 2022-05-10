package com.conference.user.port.inbound;

import com.conference.user.dto.UserChangeRequestDto;
import com.conference.user.dto.UserRequestDto;
import com.conference.user.dto.UserResponseDto;

public interface UserComponent {
    UserResponseDto saveUser(UserRequestDto userRequestDto);
    UserResponseDto changeNickname(UserChangeRequestDto userChangeRequestDto);
}
