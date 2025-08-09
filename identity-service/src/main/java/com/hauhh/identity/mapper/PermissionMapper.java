package com.hauhh.identity.mapper;

import com.hauhh.identity.dto.request.PermissionRequest;
import com.hauhh.identity.dto.response.PermissionResponse;
import com.hauhh.identity.entity.Permission;
import org.mapstruct.Mapper;



@Mapper(componentModel = "spring")
public interface PermissionMapper {
    Permission toPermission(PermissionRequest request);

    PermissionResponse toPermissionResponse(Permission permission);
}
