package com.dosecare.service.impl;

import com.dosecare.dto.request.LoginRequest;
import com.dosecare.dto.request.RegisterRequest;
import com.dosecare.dto.response.AuthResponse;
import com.dosecare.entity.User;
import com.dosecare.repository.UserRepository;
import com.dosecare.service.AuthService;
import com.dosecare.utils.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final JwtUtil jwtUtil;

    @Override
    public void register(RegisterRequest registerRequest) {
        User user=User.builder()
                .fullName(registerRequest.getFullName())
                .email(registerRequest.getEmail())
                .passwordHashed(passwordEncoder.encode(registerRequest.getPassword()))
                .phone(registerRequest.getPhone())
                .userRole(registerRequest.getRole())
                .build();
        userRepository.save(user);
    }

    @Override
    public AuthResponse login(LoginRequest loginRequest) throws RuntimeException {
        User user=userRepository.findByEmail(loginRequest.getEmail()).orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPasswordHashed())) {
            throw new RuntimeException("Invalid Credentials");
        }

        AuthResponse authResponse=new AuthResponse();
        authResponse.token=jwtUtil.generateToken(user.getEmail());
        return authResponse;
    }
}
