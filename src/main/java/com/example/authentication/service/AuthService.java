//package com.example.authentication.service;
//
//import com.example.authentication.dto.AuthResponse;
//import lombok.RequiredArgsConstructor;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.UUID;
//
//@Service
//@RequiredArgsConstructor
//public class AuthService {
//    private final TokenService tokenService;
//    private final PasswordEncoder passwordEncoder;
//
//    public AuthResponse getTokens(String username) {
//        String uuidToken = tokenService.generateUuidToken();
//        String jwtToken = generateJwtToken(username);
//        return new AuthResponse(uuidToken, jwtToken);
//    }
//
//    {
////        String uuidToken = tokenService.generateUuidToken();
////        String jwtToken = generateJwtToken(username);
//    }   public void register(String username, String password, List<String> roles)
//
//    public String generateJwtToken(String username) {
//        return tokenService.generateJwtToken(username);
//    }
//
//    public String generateUuidToken() {
//        return UUID.randomUUID().toString();
//    }
//}
