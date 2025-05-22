package com.example.authentication.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configuration.OAuth2AuthorizationServerConfiguration;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configurers.OAuth2AuthorizationServerConfigurer;
import org.springframework.security.oauth2.server.authorization.settings.AuthorizationServerSettings;
import org.springframework.security.oauth2.server.resource.introspection.OpaqueTokenIntrospector;
import org.springframework.security.oauth2.server.resource.introspection.SpringOpaqueTokenIntrospector;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.security.web.util.matcher.RequestMatcher;

import static org.springframework.security.web.util.matcher.AntPathRequestMatcher.antMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
//    @Order(1)
    public SecurityFilterChain authorizationServerSecurityFilterChain(HttpSecurity http) throws Exception {
        OAuth2AuthorizationServerConfigurer authorizationServerConfigurer = new OAuth2AuthorizationServerConfigurer();
        RequestMatcher requestMatcher = authorizationServerConfigurer.getEndpointsMatcher();

//        authorizationServerConfigurer.tokenIntrospectionEndpoint(
//                opaqueTokenConfigurer -> opaqueTokenConfigurer
//                        .authenticationProvider(new CustomAuthenticationProvider())
//        );

        authorizationServerConfigurer.oidc(oidc -> oidc
                .clientRegistrationEndpoint(Customizer.withDefaults())
                .userInfoEndpoint(Customizer.withDefaults())
        );

        http
                .cors(Customizer.withDefaults())
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/oauth2/token", "/userinfo").permitAll()
                        .anyRequest().authenticated())
                .csrf(AbstractHttpConfigurer::disable)
                .securityMatchers(securityMatchers -> securityMatchers
                        .requestMatchers(antMatcher("/oauth2/**"), requestMatcher))
                .with(authorizationServerConfigurer, authorizationServer -> authorizationServer
                        .oidc(Customizer.withDefaults())) // enable openid
                .exceptionHandling(exceptions -> exceptions
                        .authenticationEntryPoint(new LoginUrlAuthenticationEntryPoint("/login")
                        ))
//                .x509(httpSecurityX509Configurer -> httpSecurityX509Configurer
//                         with mutual TLS
//                        .subjectPrincipalRegex("CN=(.*)"))
                .oauth2ResourceServer(oauth2 -> oauth2
                                .jwt(Customizer.withDefaults())
//                                .opaqueToken(opaqueTokenConfigurer -> opaqueTokenConfigurer
//                                        .introspector(new CustomOpaqueTokenInspector())
//                                        .introspectionUri("http://localhost:8081/oauth2/introspect")
//                                        .introspectionClientCredentials("clientid", "clientsecret")
//                                )
                );

        return http
                .formLogin(Customizer.withDefaults())
                .build();
    }

//    @Bean
//    @Order(2)
//    // config for general app security
//    public SecurityFilterChain defaultAuthorizationServerSecurityFilterChain(HttpSecurity http) throws Exception {
//        http
//                .authorizeHttpRequests(authorize -> authorize
//                        .requestMatchers("/login", "/error").permitAll()
//                        .anyRequest().authenticated()
//                )
//                .csrf(AbstractHttpConfigurer::disable)
//                .formLogin(login -> login.loginPage("/login"));
//
//        return http.build();
//    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthorizationServerSettings authorizationServerSettings() {
        return AuthorizationServerSettings.builder().build();
    }

    // opaque token
//    @Bean
//    public OpaqueTokenIntrospector introspector() {
//        return new CustomOpaqueTokenInspector();
//        return new SpringOpaqueTokenIntrospector("http://localhost:8081/oauth2/introspect", "clientid", "clientsecret");
//    }

//    @Bean
//    public AuthenticationProvider authenticationProvider(PasswordEncoder passwordEncoder) {
//        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
//        authenticationProvider.setPasswordEncoder(passwordEncoder);
//        authenticationProvider.setUserDetailsService(new InMemoryUserDetailsManager());
//        return authenticationProvider;
//    }
//
//    @Bean
//    // for DaoAuthenticationProvider
//    public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder, CredentialProperties credentialProperties) {
//        var user = User.builder()
//                .username(credentialProperties.username())
//                .password(passwordEncoder.encode(credentialProperties.password()))
//                .roles(credentialProperties.roles())
//                .build();
//        return new InMemoryUserDetailsManager(user);
//    }

}

