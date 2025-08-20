package com.hauhh.identity.controller;

import java.util.List;

import com.hauhh.identity.dto.ResponseData;
import com.hauhh.identity.dto.request.RoleRequest;
import com.hauhh.identity.dto.response.RoleResponse;
import com.hauhh.identity.service.RoleService;
import org.springframework.web.bind.annotation.*;


import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/roles")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class RoleController {
    RoleService roleService;

    @PostMapping
    ResponseData<RoleResponse> create(@RequestBody RoleRequest request) {
        return ResponseData.<RoleResponse>builder()
                .result(roleService.create(request))
                .build();
    }

    @GetMapping
    ResponseData<List<RoleResponse>> getAll() {
        return ResponseData.<List<RoleResponse>>builder()
                .result(roleService.getAll())
                .build();
    }

    @DeleteMapping("/{role}")
    ResponseData<Void> delete(@PathVariable String role) {
        roleService.delete(role);
        return ResponseData.<Void>builder().build();
    }
}
