package com.dosecare.service;

import com.dosecare.dto.request.LoginRequest;
import com.dosecare.dto.request.RegisterRequest;
import com.dosecare.dto.response.AuthResponse;



public interface AuthService {

    public void register(RegisterRequest registerRequest);

    public AuthResponse login(LoginRequest loginRequest) throws RuntimeException;
}
