package com.hauhh.profile.controller;

import com.hauhh.profile.dto.BasedResponse;
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
    BasedResponse<UserProfileResponse> createProfile(@RequestBody UserProfileCreationRequest request) {
        return BasedResponse.created(userProfileService.createProfile(request), "Create Profile");
    }

    @GetMapping("/users/{profileId}")
    BasedResponse<UserProfileResponse> getProfile(@PathVariable String profileId) {
        return  BasedResponse.ok(userProfileService.getProfile(profileId), "Get Profile");
    }
}
