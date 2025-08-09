package com.hauhh.profile.mapper;

import com.hauhh.profile.dto.request.UserProfileCreationRequest;
import com.hauhh.profile.dto.response.UserProfileResponse;
import com.hauhh.profile.entity.UserProfile;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserProfileMapper {

    UserProfile toUserProfile(UserProfileCreationRequest userProfileCreationRequest);

    UserProfileResponse toUserProfileCreationResponse(UserProfile userProfile);
}
