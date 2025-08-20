package com.hauhh.profile.controller;

import com.hauhh.profile.dto.ResponseData;
import com.hauhh.profile.dto.request.UserProfileCreationRequest;
import com.hauhh.profile.dto.response.UserProfileResponse;
import com.hauhh.profile.service.UserProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UserProfileController {

    private final UserProfileService userProfileService;

    @PostMapping("/users")
    ResponseData<UserProfileResponse> createProfile(@RequestBody UserProfileCreationRequest request) {
        return ResponseData.created(userProfileService.createProfile(request), "Create Profile");
    }

    @GetMapping("/users/{profileId}")
    ResponseData<UserProfileResponse> getProfile(@PathVariable String profileId) {
        return  ResponseData.ok(userProfileService.getProfile(profileId), "Get Profile");
    }
}
