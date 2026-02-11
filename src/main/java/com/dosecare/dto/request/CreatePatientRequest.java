package com.dosecare.dto.request;


import com.dosecare.enums.BloodGroup;
import com.dosecare.enums.Gender;
import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CreatePatientRequest {

    private String fullName;

    private Integer age;

    private Gender gender;

    private LocalDate dateOfBirth;

    private BloodGroup bloodGroup;

    private String medicalConditions;

    private String emergencyContactName;

    private String emergencyContactPhone;
}
