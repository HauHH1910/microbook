package com.hauhh.identity.controller;

import java.util.List;

import com.hauhh.identity.dto.ResponseData;
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
    ResponseData<UserResponse> createUser(@RequestBody @Valid UserCreationRequest request) {
        return ResponseData.<UserResponse>builder()
                .result(userService.createUser(request))
                .build();
    }

    @GetMapping
    ResponseData<List<UserResponse>> getUsers() {
        return ResponseData.<List<UserResponse>>builder()
                .result(userService.getUsers())
                .build();
    }

    @GetMapping("/{userId}")
    ResponseData<UserResponse> getUser(@PathVariable("userId") String userId) {
        return ResponseData.<UserResponse>builder()
                .result(userService.getUser(userId))
                .build();
    }

    @GetMapping("/my-info")
    ResponseData<UserResponse> getMyInfo() {
        return ResponseData.<UserResponse>builder()
                .result(userService.getMyInfo())
                .build();
    }

    @DeleteMapping("/{userId}")
    ResponseData<String> deleteUser(@PathVariable String userId) {
        userService.deleteUser(userId);
        return ResponseData.<String>builder().result("User has been deleted").build();
    }

    @PutMapping("/{userId}")
    ResponseData<UserResponse> updateUser(@PathVariable String userId, @RequestBody UserUpdateRequest request) {
        return ResponseData.<UserResponse>builder()
                .result(userService.updateUser(userId, request))
                .build();
    }
}
