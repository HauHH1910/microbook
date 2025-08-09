package com.hauhh.identity.controller;

import java.util.List;

import com.hauhh.identity.dto.BasedResponse;
import com.hauhh.identity.dto.request.PermissionRequest;
import com.hauhh.identity.dto.response.PermissionResponse;
import com.hauhh.identity.service.PermissionService;
import org.springframework.web.bind.annotation.*;


import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/permissions")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class PermissionController {
    PermissionService permissionService;

    @PostMapping
    BasedResponse<PermissionResponse> create(@RequestBody PermissionRequest request) {
        return BasedResponse.<PermissionResponse>builder()
                .result(permissionService.create(request))
                .build();
    }

    @GetMapping
    BasedResponse<List<PermissionResponse>> getAll() {
        return BasedResponse.<List<PermissionResponse>>builder()
                .result(permissionService.getAll())
                .build();
    }

    @DeleteMapping("/{permission}")
    BasedResponse<Void> delete(@PathVariable String permission) {
        permissionService.delete(permission);
        return BasedResponse.<Void>builder().build();
    }
}
