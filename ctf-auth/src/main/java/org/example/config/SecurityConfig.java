package org.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                        // 允许访问认证相关接口
                        .requestMatchers("/user/register", "/user/login", "/user/sendCaptcha", "/user/resetPassword", "/user/verifyCaptcha", "/user/verifyToken").permitAll()
                        // 允许访问actuator端点
                        .requestMatchers("/actuator/**").permitAll()
                        // 其他请求需要认证
                        .anyRequest().authenticated()
                );

        return http.build();
    }
}
