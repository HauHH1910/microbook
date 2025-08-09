package com.hauhh.profile.service;

import com.hauhh.profile.dto.request.UserProfileCreationRequest;
import com.hauhh.profile.dto.response.UserProfileResponse;
import com.hauhh.profile.entity.UserProfile;
import com.hauhh.profile.mapper.UserProfileMapper;
import com.hauhh.profile.repository.UserProfileRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j(topic = "[User Profile Service]")
public class UserProfileService {

    private final UserProfileRepository userProfileRepository;
    private final UserProfileMapper userProfileMapper;


    public UserProfileResponse createProfile(UserProfileCreationRequest request) {

        UserProfile userProfile = userProfileRepository.save(
                userProfileMapper.toUserProfile(request)
        );

        return userProfileMapper.toUserProfileCreationResponse(userProfile);
    }

    public UserProfileResponse getProfile(String id){
        UserProfile userProfile = userProfileRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Profile Not Found"));

        return userProfileMapper.toUserProfileCreationResponse(userProfile);
    }
}
