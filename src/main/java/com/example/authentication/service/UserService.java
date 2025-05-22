package com.example.authentication.service;

import com.example.authentication.dao.AuthorityEntity;
import com.example.authentication.dao.UserEntity;
import com.example.authentication.dto.UserResponse;
import com.example.authentication.repository.AuthorityRepository;
import com.example.authentication.repository.UserRepository;
import com.example.authentication.util.ClientRegistryUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.util.Objects.nonNull;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final AuthorityRepository authorityRepository;
    private final PasswordEncoder passwordEncoder;
    private final RegisteredClientRepository registeredClientRepository;

    public void register(String username, String password, List<String> roles) {
        if (userRepository.existsByUsername(username)) {
            throw new IllegalArgumentException("User with username " + username + " already exists");
        }
        UserEntity userEntity = new UserEntity(username, passwordEncoder.encode(password), true);
        userRepository.save(userEntity);
        if (roles != null && !roles.isEmpty()) {
            for (String role : roles) {
                AuthorityEntity authorityEntity = new AuthorityEntity();
                authorityEntity.setAuthority(role);
                authorityEntity.setUsername(username);
                authorityRepository.save(authorityEntity);
            }
        }

        RegisteredClient registeredClient = ClientRegistryUtil.toRegisteredClient(username, passwordEncoder.encode(password));
        registeredClientRepository.save(registeredClient);
    }

    public List<UserDetails> getAllUserDetails() {
        return Optional.of(userRepository.findAll())
                .map(users -> users.stream()
                        .map(this::getUserDetails)
                        .toList()
                ).orElse(new ArrayList<>());
    }

    public List<UserResponse> getAllUsers() {
        return Optional.of(userRepository.findAll())
                .map(users ->
                        users.stream()
                                .map(user -> new UserResponse(user.getUsername(), user.isEnabled()))
                                .toList()
                ).orElse(new ArrayList<>());
    }

    public UserDetails getUserByUsername(String username) {
        UserEntity user = userRepository.findByUsername(username);
        return nonNull(user) ? getUserDetails(user) : null;
    }

    private UserDetails getUserDetails(UserEntity user) {
        String[] roles = (String[]) authorityRepository.findByUsername(user.getUsername()).stream()
                .map(AuthorityEntity::getAuthority).toArray();
        return User.builder()
                .username(user.getUsername())
                .password(passwordEncoder.encode(user.getPassword()))
                .roles(roles)
                .build();
    }
}
