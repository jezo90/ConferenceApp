package com.conference.user;

import com.conference.user.dto.UserChangeRequestDto;
import com.conference.user.dto.UserRequestDto;
import com.conference.user.dto.UserResponseDto;
import com.conference.user.port.inbound.UserComponent;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
class UserFacade implements UserComponent {
    private final UserService userService;

    @Override
    public UserResponseDto saveUser(UserRequestDto userRequestDto) {

        return userService.saveUser(userRequestDto);
    }

    @Override
    public UserResponseDto changeEmail(UserChangeRequestDto userChangeRequestDto) {
        return userService.changeEmail(userChangeRequestDto);
    }

    @Override
    public List<UserResponseDto> getAllUsers() {
        return userService.getAllUsers();
    }
}
