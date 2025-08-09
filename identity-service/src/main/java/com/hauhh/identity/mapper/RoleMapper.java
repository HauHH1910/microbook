package com.hauhh.identity.mapper;

import com.hauhh.identity.dto.request.RoleRequest;
import com.hauhh.identity.dto.response.RoleResponse;
import com.hauhh.identity.entity.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    @Mapping(target = "permissions", ignore = true)
    Role toRole(RoleRequest request);

    RoleResponse toRoleResponse(Role role);
}
