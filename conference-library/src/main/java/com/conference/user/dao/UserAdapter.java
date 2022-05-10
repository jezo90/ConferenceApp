package com.conference.user.dao;

import com.conference.user.dto.UserChangeRequestDto;
import com.conference.user.dto.UserDto;
import com.conference.user.dto.UserRequestDto;
import com.conference.user.dto.UserResponseDto;
import com.conference.user.port.outbound.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
class UserAdapter implements UserRepository {
    private final UserSpringRepository userSpringRepository;

    @Override
    public UserResponseDto saveUser(UserRequestDto userRequestDto) {

        return UserEntityMapper.map(
                userSpringRepository.save(
                UserEntityMapper.map(userRequestDto)));
    }

    @Override
    public Optional<UserDto> findByUsername(String username) {
        return userSpringRepository.findByUsername(username)
                .map(UserEntityMapper::mapToDto);
    }

    @Override
    public UserResponseDto changeNickname(UserChangeRequestDto userChangeRequestDto) {
        return UserEntityMapper.map(
                userSpringRepository.save(
                UserEntityMapper.map(userChangeRequestDto)));
    }
}
