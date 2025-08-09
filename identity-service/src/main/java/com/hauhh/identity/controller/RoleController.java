package com.hauhh.identity.controller;

import java.util.List;

import com.hauhh.identity.dto.BasedResponse;
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
    BasedResponse<RoleResponse> create(@RequestBody RoleRequest request) {
        return BasedResponse.<RoleResponse>builder()
                .result(roleService.create(request))
                .build();
    }

    @GetMapping
    BasedResponse<List<RoleResponse>> getAll() {
        return BasedResponse.<List<RoleResponse>>builder()
                .result(roleService.getAll())
                .build();
    }

    @DeleteMapping("/{role}")
    BasedResponse<Void> delete(@PathVariable String role) {
        roleService.delete(role);
        return BasedResponse.<Void>builder().build();
    }
}
