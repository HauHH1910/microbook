package com.hauhh.profile.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserProfileCreationRequest {

    private String firstName;

    private String lastName;

    private LocalDate dateOfBirth;

    private String city;
}
