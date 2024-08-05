package com.diefthyntis.ConnexionWebapp.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(authorizeRequests ->
                authorizeRequests
                    .requestMatchers("/register", "/login").permitAll() // Permet l'accès sans authentification
                    
                    .anyRequest().authenticated() // Requiert une authentification pour toutes les autres requêtes
            
            		)
            .formLogin(formLogin ->
                formLogin
                    .loginPage("/login") // Spécifie la page de connexion personnalisée
                    .failureUrl("/login?error=true")
                    .defaultSuccessUrl("/welcome", true) // Redirige vers /welcome après une connexion réussie
            )
            .logout(logout ->
                logout.logoutSuccessUrl("/login") // Redirige vers /login après une déconnexion réussie
            );

        return http.build();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // Utilise BCrypt pour le cryptage des mots de passe
    }
}
