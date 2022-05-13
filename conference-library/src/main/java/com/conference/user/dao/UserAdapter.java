package com.conference.user.dao;

import com.conference.user.dto.UserChangeRequestDto;
import com.conference.user.dto.UserDto;
import com.conference.user.dto.UserRequestDto;
import com.conference.user.dto.UserResponseDto;
import com.conference.user.port.outbound.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
class UserAdapter implements UserRepository {
    private final UserSpringRepository userSpringRepository;
    private final UserEntityMapper userEntityMapper = new UserEntityMapper();

    @Override
    public UserResponseDto saveUser(UserRequestDto userRequestDto) {

        return userEntityMapper.map(
                userSpringRepository.save(
                        userEntityMapper.map(userRequestDto)));
    }

    @Override
    public Optional<UserDto> findByUsername(String username) {
        return userSpringRepository.findByUsername(username)
                .map(userEntityMapper::mapToDto);
    }

    @Override
    public UserResponseDto changeEmail(UserDto userDto) {
        return userEntityMapper.map(
                userSpringRepository.save(
                        userEntityMapper.map(userDto)));
    }

    @Override
    public Optional<Long> findIdByUsernameAndEmail(String username, String email) {
        return userSpringRepository.findByUsernameAndEmail(username, email).map(UserEntity::getId);
    }

    @Override
    public List<UserResponseDto> getAllUsers() {
        return userSpringRepository.findAll().stream().map(userEntityMapper::map).toList();
    }
}
