package com.conference.user;

import com.conference.exception.CustomException;
import com.conference.user.dto.UserChangeRequestDto;
import com.conference.user.dto.UserDto;
import com.conference.user.dto.UserRequestDto;
import com.conference.user.dto.UserResponseDto;
import com.conference.user.port.outbound.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@RequiredArgsConstructor
@Service
class UserService {
    private final UserRepository userRepository;

    public UserResponseDto saveUser(UserRequestDto userRequestDto) {

        checkIfUsernameIsAvailable(userRequestDto.username());

        return userRepository.saveUser(userRequestDto);
    }

    private void checkIfUsernameIsAvailable(String username)
    {
        if (userRepository.findByUsername(username).isPresent()) {
            throw new CustomException("Podany login jest już zajęty", 500);
        }
    }

    public UserResponseDto changeEmail(UserChangeRequestDto userChangeRequestDto) {

        UserDto userDto = getUserByUsername(userChangeRequestDto.username());

        checkIfEmailCurrentEqualsPassed(userDto.email(), userChangeRequestDto.currentEmail());

        return userRepository.changeEmail(
                userDto.setEmail(userChangeRequestDto.newEmail()));
    }

    private UserDto getUserByUsername(String username)
    {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new CustomException("Użytkownik o podanym loginie nie istnieje", 500));
    }

    private void checkIfEmailCurrentEqualsPassed(String current, String passed)
    {
        if (!current.equals(passed)) {
            throw new CustomException("Aktualnie przypisany email do konta się nie zgadza", 500);
        }
    }

    public List<UserResponseDto> getAllUsers() {
        return userRepository.getAllUsers();
    }


}
