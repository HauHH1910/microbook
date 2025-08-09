package com.hauhh.identity.controller;

import java.util.List;

import com.hauhh.identity.dto.BasedResponse;
import com.hauhh.identity.dto.request.UserCreationRequest;
import com.hauhh.identity.dto.request.UserUpdateRequest;
import com.hauhh.identity.dto.response.UserResponse;
import com.hauhh.identity.service.UserService;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.*;


import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class UserController {
    UserService userService;

    @PostMapping
    BasedResponse<UserResponse> createUser(@RequestBody @Valid UserCreationRequest request) {
        return BasedResponse.<UserResponse>builder()
                .result(userService.createUser(request))
                .build();
    }

    @GetMapping
    BasedResponse<List<UserResponse>> getUsers() {
        return BasedResponse.<List<UserResponse>>builder()
                .result(userService.getUsers())
                .build();
    }

    @GetMapping("/{userId}")
    BasedResponse<UserResponse> getUser(@PathVariable("userId") String userId) {
        return BasedResponse.<UserResponse>builder()
                .result(userService.getUser(userId))
                .build();
    }

    @GetMapping("/my-info")
    BasedResponse<UserResponse> getMyInfo() {
        return BasedResponse.<UserResponse>builder()
                .result(userService.getMyInfo())
                .build();
    }

    @DeleteMapping("/{userId}")
    BasedResponse<String> deleteUser(@PathVariable String userId) {
        userService.deleteUser(userId);
        return BasedResponse.<String>builder().result("User has been deleted").build();
    }

    @PutMapping("/{userId}")
    BasedResponse<UserResponse> updateUser(@PathVariable String userId, @RequestBody UserUpdateRequest request) {
        return BasedResponse.<UserResponse>builder()
                .result(userService.updateUser(userId, request))
                .build();
    }
}
