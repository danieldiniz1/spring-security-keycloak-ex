package br.com.train.springkeycloack.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .httpBasic(AbstractHttpConfigurer::disable)
                .csrf(AbstractHttpConfigurer::disable)
//                .addFilterBefore(customFilter, UsernamePasswordAuthenticationFilter.class)
                .sessionManagement(
                        session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .authorizeHttpRequests(authorizationMatchers ->
                        authorizationMatchers
                                .requestMatchers("/v1/auth/**",
                                        "/v1/refresh/**",
                                        "/swagger-ui/**",
                                        "/v3/api-docs/**").permitAll()
//                                .requestMatchers("/users/**").hasRole("USER")
//                                .requestMatchers("/admin/**").hasRole("ADMIN")
//                                .requestMatchers("/v1/test/test/**").hasAnyRole("ADMIN", "USER","COMMON_USER","MANAGER")
//                                .requestMatchers("/actuator/**").hasRole("ACTUATOR")
//                                .requestMatchers("/health/**").hasRole("HEALTH")
//                                .requestMatchers("/users").denyAll()
                                .anyRequest().authenticated()
                )
                .cors( cors -> {})
                .build();
    }
}
