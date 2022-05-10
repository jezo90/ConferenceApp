package com.conference.user;

import com.conference.exception.EntityNotFoundException;
import com.conference.user.dto.UserChangeRequestDto;
import com.conference.user.dto.UserDto;
import com.conference.user.dto.UserRequestDto;
import com.conference.user.dto.UserResponseDto;
import com.conference.user.port.outbound.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
class UserService {
    private final UserRepository userRepository;

    public UserResponseDto saveUser(UserRequestDto userRequestDto) {

        if(userRepository.findByUsername(userRequestDto.username()).isPresent())
        {
            throw new EntityNotFoundException("Podany login jest już zajęty", 500);
        }

        return userRepository.saveUser(userRequestDto);
    }

    public UserResponseDto changeNickname(UserChangeRequestDto userChangeRequestDto) {

        UserDto userDto = userRepository.findByUsername(userChangeRequestDto.username())
                .orElseThrow(() -> new EntityNotFoundException("Użytkownik o podanym loginie nie istnieje", 500));
        if(!userChangeRequestDto.currentEmail().equals(userDto.email()))
        {
            throw new EntityNotFoundException("Aktualnie przypisany email do konta się nie zgadza", 500);
        }

        return userRepository.changeNickname(userChangeRequestDto);
    }
}
