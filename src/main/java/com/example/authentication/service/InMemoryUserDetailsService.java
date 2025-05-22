//package com.example.authentication.service;
//
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import java.util.*;
//
//@Service
//public class InMemoryUserDetailsService implements UserDetailsService {
//    private final UserService userService;
//
//    public InMemoryUserDetailsService(UserService userService) {
//        this.userService = userService;
//    }
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        return Optional.ofNullable(userService.getUserByUsername(username))
//                .orElseThrow(() ->new UsernameNotFoundException(username));
//    }
//}
