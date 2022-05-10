package com.conference.user.api;

import com.conference.user.mapper.UserMapper;
import com.conference.user.model.UserChangeRequest;
import com.conference.user.model.UserRequest;
import com.conference.user.model.UserResponse;
import com.conference.user.port.inbound.UserComponent;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {

    private final UserComponent userComponent;

    @PostMapping("/add")
    ResponseEntity<UserResponse> add(@RequestBody UserRequest userRequest) {
        return ResponseEntity.ok(
                UserMapper.map(
                        userComponent.saveUser(
                                UserMapper.map(userRequest))));
    }

    @PutMapping("/update")
    ResponseEntity<UserResponse> add(@RequestBody UserChangeRequest userChangeRequest) {
        return ResponseEntity.ok(
                UserMapper.map(
                        userComponent.changeNickname(
                                UserMapper.map(userChangeRequest))));
    }
}
