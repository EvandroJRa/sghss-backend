package com.sghss.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;


@Configuration // Marca a classe como uma classe de configuração do Spring.
@EnableWebSecurity // Habilita a configuração de segurança web customizada.
public class SecurityConfigurations {

    @Bean // Expõe o objeto retornado por este método para ser gerenciado pelo Spring.
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf.disable()) // Desabilita CSRF, pois usaremos tokens (API stateless).
                .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // Sessão será stateless.
                .authorizeHttpRequests(req -> {
                    // AQUI ESTÁ A MÁGICA:
                    // Permite requisições POST para /api/login sem autenticação.
                    req.requestMatchers(HttpMethod.POST, "/api/login").permitAll();
                    // Permite requisições POST para /api/pacientes sem autenticação (para o cadastro).
                    req.requestMatchers(HttpMethod.POST, "/api/pacientes").permitAll();
                    // Para qualquer outra requisição, o usuário precisa estar autenticado.
                    req.anyRequest().authenticated();
                })
                .build();
    }

    @Bean // Expõe o PasswordEncoder para injetá-lo em outras classes (como no Service).
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }
}
