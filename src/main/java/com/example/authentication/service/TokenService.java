package com.example.authentication.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;
import java.util.UUID;

@Slf4j
@Service
public class TokenService {

    private static final String SECRET_KEY = "SECRET_KEY"; // 24 hrs
    private static final Long JWT_EXPIRATION = 86400000L; // 24 hrs

    public String generateJwtToken(String username) {
        return Jwts.builder()
//                .setId(id)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + JWT_EXPIRATION))
                .setSubject(username)
//                .setIssuer(issuer)
                .signWith(SignatureAlgorithm.HS256, getSigningKey())
                .compact();
    }

    private static Key getSigningKey() {
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(SECRET_KEY);
        return new SecretKeySpec(apiKeySecretBytes, SignatureAlgorithm.HS256.getJcaName());
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(getSigningKey()).parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            log.error("Error validating the token: {}",e.getMessage());
            return false;
        }
    }

    public static String generateUuidToken() {
        return UUID.randomUUID().toString();
    }
}
