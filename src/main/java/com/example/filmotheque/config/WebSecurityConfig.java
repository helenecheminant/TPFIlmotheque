package com.example.filmotheque.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {


    @Bean //@ ce bean sert à empecher des user d'atteindre les proutes non autorisées
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/", "/catalogFilms", "/film/details{id}", "/error").permitAll()
                        .requestMatchers("/nouveauFilmForm","/nouveauAvisForm").authenticated()
                        .anyRequest().authenticated()

                )
                .formLogin((form) -> form // écran de login personalisé
                        .loginPage("/login")
                        .permitAll()
                )
                .logout((logout) -> logout
                        .permitAll()
                        .logoutSuccessUrl("/catalogFilms"));

        return http.build();
    }

    // un bean pour le user en mémoire
  /*  @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user =
                User.withDefaultPasswordEncoder()
                        .username("user")
                        .password("password")
                        .roles("ADMIN")
                        .build();

        return new InMemoryUserDetailsManager(user);
    }*/
    @Bean
    public PasswordEncoder passwordEncoder() {
       // return new BCryptPasswordEncoder();
        //return NoOpPasswordEncoder.getInstance();
       return PasswordEncoderFactories.createDelegatingPasswordEncoder();

    }
}

