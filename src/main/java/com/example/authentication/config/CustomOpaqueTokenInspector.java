//package com.example.authentication.config;
//
//import org.springframework.security.oauth2.core.OAuth2AuthenticatedPrincipal;
//import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
//import org.springframework.security.oauth2.core.OAuth2Error;
//import org.springframework.security.oauth2.core.OAuth2TokenIntrospectionClaimNames;
//import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
//import org.springframework.security.oauth2.server.resource.introspection.OAuth2IntrospectionAuthenticatedPrincipal;
//import org.springframework.security.oauth2.server.resource.introspection.OpaqueTokenIntrospector;
//import org.springframework.stereotype.Component;
//
//import java.util.Map;
//
//@Component
//public class CustomOpaqueTokenInspector implements OpaqueTokenIntrospector {
//
//    @Override
//    public OAuth2AuthenticatedPrincipal introspect(String token) {
//        if (isValidToken(token)) {
//            return new CustomAuthenticatedPrincipal(Map.of(
//                    OAuth2TokenIntrospectionClaimNames.SUB, "subject",
//                    "scope", "read write openid",
//                    "custom_clain", "cutom claim value"
//            ));
//        } else {
//            throw new OAuth2AuthenticationException(new OAuth2Error("Invalid token", "Invalid token", null));
//        }
//    }
//
//    private boolean isValidToken(String token) {
//        return token.startsWith("Bearer");
//    }
//}
