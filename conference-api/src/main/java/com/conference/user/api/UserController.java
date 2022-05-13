package com.conference.user.api;

import com.conference.user.mapper.UserMapper;
import com.conference.user.model.UserChangeRequest;
import com.conference.user.model.UserRequest;
import com.conference.user.model.UserResponse;
import com.conference.user.port.inbound.UserComponent;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {

    private final UserComponent userComponent;
    private final UserMapper userMapper = new UserMapper();

    @PostMapping("/add")
    ResponseEntity<UserResponse> add(@RequestBody UserRequest userRequest) {
        return ResponseEntity.ok(
                userMapper.map(
                        userComponent.saveUser(
                                userMapper.map(userRequest))));
    }

    @PutMapping("/update")
    ResponseEntity<UserResponse> update(@RequestBody UserChangeRequest userChangeRequest) {
        return ResponseEntity.ok(
                userMapper.map(
                        userComponent.changeEmail(
                                userMapper.map(userChangeRequest))));
    }

    @GetMapping("/all")
    ResponseEntity<List<UserResponse>> allUsers()
    {
        return ResponseEntity.ok(
                userComponent.getAllUsers()
                        .stream().map(userMapper::map).toList());
    }

}
