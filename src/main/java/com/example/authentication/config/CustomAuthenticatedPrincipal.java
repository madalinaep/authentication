//package com.example.authentication.config;
//
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.oauth2.core.OAuth2AuthenticatedPrincipal;
//import org.springframework.stereotype.Component;
//
//import java.util.Collection;
//import java.util.Collections;
//import java.util.Map;
//
//@Component
//public class CustomAuthenticatedPrincipal implements OAuth2AuthenticatedPrincipal {
//    private final Map<String, Object> claims;
//
//    public CustomAuthenticatedPrincipal(Map<String, Object> claims) {
//        this.claims = claims;
//    }
//
//    @Override
//    public Map<String, Object> getAttributes() {
//        return claims;
//    }
//
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"));
//    }
//
//    @Override
//    public String getName() {
//        return claims.get("sub").toString();
//    }
//}
