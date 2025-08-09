package com.hauhh.identity.controller;

import java.text.ParseException;

import com.hauhh.identity.dto.BasedResponse;
import com.hauhh.identity.dto.request.*;
import com.hauhh.identity.dto.response.AuthenticationResponse;
import com.hauhh.identity.dto.response.IntrospectResponse;
import com.hauhh.identity.service.AuthenticationService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.nimbusds.jose.JOSEException;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationController {
    AuthenticationService authenticationService;

    @PostMapping("/token")
    BasedResponse<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
        var result = authenticationService.authenticate(request);
        return BasedResponse.<AuthenticationResponse>builder().result(result).build();
    }

    @PostMapping("/introspect")
    BasedResponse<IntrospectResponse> authenticate(@RequestBody IntrospectRequest request)
            throws ParseException, JOSEException {
        var result = authenticationService.introspect(request);
        return BasedResponse.<IntrospectResponse>builder().result(result).build();
    }

    @PostMapping("/refresh")
    BasedResponse<AuthenticationResponse> authenticate(@RequestBody RefreshRequest request)
            throws ParseException, JOSEException {
        var result = authenticationService.refreshToken(request);
        return BasedResponse.<AuthenticationResponse>builder().result(result).build();
    }

    @PostMapping("/logout")
    BasedResponse<Void> logout(@RequestBody LogoutRequest request) throws ParseException, JOSEException {
        authenticationService.logout(request);
        return BasedResponse.<Void>builder().build();
    }
}
