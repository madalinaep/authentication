//package com.example.authentication.repository;
//
//import com.example.authentication.dao.AuthorityEntity;
//import com.example.authentication.dao.UserEntity;
//import com.example.authentication.util.ClientRegistryUtil;
//import lombok.RequiredArgsConstructor;
//import org.springframework.context.annotation.Primary;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.oauth2.core.AuthorizationGrantType;
//import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
//import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
//import org.springframework.stereotype.Component;
//
//import java.security.spec.PSSParameterSpec;
//import java.util.Optional;
//import java.util.Set;
//
//@Component
//@Primary
//@RequiredArgsConstructor
//public class JpaRegisteredClientRepository  implements RegisteredClientRepository {
//    private  final UserRepository userRepository;
//    private  final PasswordEncoder passwordEncoder;
//    private  final AuthorityRepository authorityRepository;
//
//    @Override
//    public void save(RegisteredClient registeredClient) {
//        String username= registeredClient.getClientId();
//        String password= registeredClient.getClientSecret();
//        Set<AuthorizationGrantType> roles = registeredClient.getAuthorizationGrantTypes();
//        if (userRepository.existsByUsername(username)) {
//            throw new IllegalArgumentException("User with username " + username + " already exists");
//        }
//        UserEntity userEntity = new UserEntity(username, passwordEncoder.encode(password), true);
//        userRepository.save(userEntity);
//        if (roles != null && !roles.isEmpty()) {
//            for (AuthorizationGrantType role : roles) {
//                AuthorityEntity authorityEntity = new AuthorityEntity();
//                authorityEntity.setAuthority(role.getValue());
//                authorityEntity.setUsername(username);
//                authorityRepository.save(authorityEntity);
//            }
//        }
//    }
//
//    @Override
//    public RegisteredClient findById(String id) {
//        return findUser(id);
//    }
//
//    @Override
//    public RegisteredClient findByClientId(String clientId) {
//        return findUser(clientId);
//    }
//
//    private RegisteredClient findUser(String username) {
//        return Optional.of(userRepository.findByUsername(username))
//                .map(user-> ClientRegistryUtil.toRegisteredClient(user.getUsername(), passwordEncoder.encode(user.getPassword())))
//                .orElse(null);
//    }
//}
