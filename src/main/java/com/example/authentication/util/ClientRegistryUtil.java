package com.example.authentication.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.oidc.OidcScopes;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.settings.ClientSettings;
import org.springframework.security.oauth2.server.authorization.settings.OAuth2TokenFormat;
import org.springframework.security.oauth2.server.authorization.settings.TokenSettings;

import java.time.Duration;
import java.util.List;
import java.util.UUID;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ClientRegistryUtil {

    public static RegisteredClient toRegisteredClient(String clientId, String password) {
        return RegisteredClient.withId(UUID.randomUUID().toString())
                .clientId(clientId)
                .clientSecret(password) // encoded password
                .scopes(scopes -> scopes.addAll(List.of(OidcScopes.OPENID, "read", "write")))
                .authorizationGrantTypes(authorizationGrantTypes -> authorizationGrantTypes.addAll(List.of(
                        AuthorizationGrantType.CLIENT_CREDENTIALS,
                        AuthorizationGrantType.AUTHORIZATION_CODE,
                        AuthorizationGrantType.REFRESH_TOKEN))
                )
                .tokenSettings(TokenSettings.builder()
                        .accessTokenTimeToLive(Duration.ofMinutes(60)) // todo configure this
                        .refreshTokenTimeToLive(Duration.ofHours(24)) // todo configure this
                        // opaque token
                        .accessTokenFormat(OAuth2TokenFormat.REFERENCE)
                        .build())
                .clientSettings(ClientSettings.builder()
                        .requireAuthorizationConsent(true) // not mandatory<
////                        .requireAuthorizationConsent(false)
////                        .requireProofKey(true)
                        .build())
                .redirectUri("http://localhost:8080/login/oauth2/code/" + clientId)
//                .postLogoutRedirectUri("http://localhost:8080/logout")
                .build();
    }
}
