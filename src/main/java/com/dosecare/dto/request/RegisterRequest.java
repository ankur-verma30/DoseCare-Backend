package com.dosecare.dto.request;

import com.dosecare.enums.UserRole;
import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class RegisterRequest {
    public String fullName;

    public String email;

    public String phone;

    public String password;

    public UserRole role;
}

