package com.conference.user;

import com.conference.user.dto.UserChangeRequestDto;
import com.conference.user.dto.UserRequestDto;
import com.conference.user.dto.UserResponseDto;
import com.conference.user.port.inbound.UserComponent;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
class UserFacade implements UserComponent {
    private final UserService userService;

    @Override
    public UserResponseDto saveUser(UserRequestDto userRequestDto) {

        return userService.saveUser(userRequestDto);
    }

    @Override
    public UserResponseDto changeNickname(UserChangeRequestDto userChangeRequestDto) {
        return userService.changeNickname(userChangeRequestDto);
    }
}
