package com.hauhh.identity.mapper;

import com.hauhh.identity.dto.request.UserCreationRequest;
import com.hauhh.identity.dto.request.UserUpdateRequest;
import com.hauhh.identity.dto.response.UserResponse;
import com.hauhh.identity.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;


@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUser(UserCreationRequest request);

    UserResponse toUserResponse(User user);

    @Mapping(target = "roles", ignore = true)
    void updateUser(@MappingTarget User user, UserUpdateRequest request);
}
