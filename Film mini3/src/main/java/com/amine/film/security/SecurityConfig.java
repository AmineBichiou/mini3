package com.amine.film.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Bean
    public InMemoryUserDetailsManager inMemoryUserDetailsManager(PasswordEncoder passwordEncoder) {
        return new InMemoryUserDetailsManager(
                User.withUsername("user").password(passwordEncoder.encode("123")).roles("USER").build(),
                User.withUsername("amine").password(passwordEncoder.encode("123")).roles("USER","AGENT").build(),
                User.withUsername("admin").password(passwordEncoder.encode("123")).roles("ADMIN").build()
        );
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.formLogin().loginPage("/login").permitAll();
        httpSecurity.authorizeRequests().requestMatchers("/webjars/**").permitAll();
        httpSecurity.authorizeRequests().requestMatchers("/showCreate").hasAnyRole("ADMIN");
        httpSecurity.authorizeRequests().requestMatchers("/saveFilm").hasAnyRole("ADMIN");
        httpSecurity.authorizeRequests().requestMatchers("/listeFilms") .hasAnyRole("ADMIN","AGENT","USER");
        httpSecurity.authorizeRequests().requestMatchers("/supprimerFilm","/modifierFilm","/updateFilm") .hasAnyRole("ADMIN");
        httpSecurity.authorizeRequests().anyRequest().authenticated();
        httpSecurity.exceptionHandling().accessDeniedPage("/accessDenied");
        httpSecurity.csrf().disable(); // disable CSRF protection

        return httpSecurity.build();
    }
}