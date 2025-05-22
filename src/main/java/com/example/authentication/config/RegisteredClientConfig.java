package com.example.authentication.config;

import com.example.authentication.util.ClientRegistryUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.server.authorization.client.InMemoryRegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;

@Configuration
public class RegisteredClientConfig {

    @Bean
    public RegisteredClientRepository registeredClientRepository(PasswordEncoder passwordEncoder) {
       // default client
        RegisteredClient registeredClient = ClientRegistryUtil.toRegisteredClient("clientid", passwordEncoder.encode("clientsecret"));
        return new InMemoryRegisteredClientRepository(registeredClient);
    }
}
