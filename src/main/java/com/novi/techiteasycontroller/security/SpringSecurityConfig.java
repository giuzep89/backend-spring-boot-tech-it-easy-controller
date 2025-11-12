package com.novi.techiteasycontroller.security;

import com.novi.techiteasycontroller.services.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig {
    private final CustomUserDetailsService customUserDetailsService;
    private final JwtRequestFilter jwtRequestFilter;

    public SpringSecurityConfig(CustomUserDetailsService customUserDetailsService, JwtRequestFilter jwtRequestFilter) {
        this.customUserDetailsService = customUserDetailsService;
        this.jwtRequestFilter = jwtRequestFilter;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http, PasswordEncoder passwordEncoder) throws Exception {
        var auth = new DaoAuthenticationProvider();
        auth.setPasswordEncoder(passwordEncoder);
        auth.setUserDetailsService(customUserDetailsService);
        return new ProviderManager(auth);
    }

    @Bean
    protected SecurityFilterChain filter(HttpSecurity http) throws Exception {

        http
                .csrf(csrf -> csrf.disable())

                .cors(Customizer.withDefaults())

                .authorizeHttpRequests(auth -> auth

                        .requestMatchers("/authenticate").permitAll()
                        .requestMatchers(HttpMethod.GET, "/televisions/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/cimodules/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/remote_controllers/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/wallbrackets/**").permitAll()

                        .requestMatchers(HttpMethod.POST, "/users/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/users/**").hasRole("ADMIN")

                        .requestMatchers(HttpMethod.POST, "/televisions/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/televisions/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/televisions/**").hasRole("ADMIN")

                        .requestMatchers(HttpMethod.POST, "/cimodules/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/cimodules/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/cimodules/**").hasRole("ADMIN")

                        .requestMatchers(HttpMethod.POST, "/wallbrackets/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/wallbrackets/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/wallbrackets/**").hasRole("ADMIN")

                        .requestMatchers(HttpMethod.POST, "/remote_controllers/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/remote_controllers/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/remote_controllers/**").hasRole("ADMIN")

                        .requestMatchers("/users/**").authenticated()

                        .anyRequest().denyAll()
                )

                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                );

        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
