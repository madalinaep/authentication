//package com.example.authentication.config;
//
//import org.springframework.security.authentication.AuthenticationProvider;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.oauth2.server.resource.authentication.BearerTokenAuthenticationToken;
//import org.springframework.stereotype.Component;
//
//@Component
//public class CustomAuthenticationProvider implements AuthenticationProvider {
//
//    @Override
//    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
//        return authentication;
//    }
//
//    @Override
//    public boolean supports(Class<?> authentication) {
//        return BearerTokenAuthenticationToken.class.isAssignableFrom(authentication);
//    }
//}
