package com.dosecare.dto.response;

import com.dosecare.enums.BloodGroup;
import com.dosecare.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class PatientResponse {

    private Long id;

    private String fullName;

    private Integer age;

    private Gender gender;

    private BloodGroup bloodGroup;

    private LocalDate dateOfBirth;

    private String medicalConditions;

    private String emergencyContactName;

    private String emergencyContactPhone;

    private Long caregiverId;

    private LocalDateTime createdAt;
}
