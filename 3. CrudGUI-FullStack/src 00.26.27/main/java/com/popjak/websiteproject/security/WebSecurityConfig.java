package com.popjak.websiteproject.security;

import org.springframework.context.annotation.*;
import org.springframework.http.*;
import org.springframework.security.config.annotation.web.builders.*;
import org.springframework.security.crypto.bcrypt.*;
import org.springframework.security.provisioning.*;
import org.springframework.security.web.*;

import javax.sql.*;

@Configuration
public class WebSecurityConfig {

    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource) {
        JdbcUserDetailsManager userDetailsManager = new JdbcUserDetailsManager(dataSource);


        String usersByUsernameQuery = "SELECT email, password, enabled FROM users WHERE LOWER(email) = LOWER(?)";
        userDetailsManager.setUsersByUsernameQuery(usersByUsernameQuery);


        String authoritiesByUsernameQuery = "SELECT email, authority FROM authorities WHERE LOWER(email) = LOWER(?)";
        userDetailsManager.setAuthoritiesByUsernameQuery(authoritiesByUsernameQuery);

        return userDetailsManager;
    }


    // CUSTOM LOGIN
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(configurer -> configurer
                        .requestMatchers(HttpMethod.POST,"/save").permitAll()
                        .requestMatchers("/register").permitAll()
                        .requestMatchers("/list/**").hasAuthority("MANAGER")
                        .anyRequest().authenticated())
                        .exceptionHandling(configurer ->configurer.accessDeniedPage("/error"))
                        .formLogin(form->form.loginPage("/showLoginPage")
                        .loginProcessingUrl("/authenticateTheUser")
                        .permitAll())
                        .logout(logout->logout.permitAll());
        return http.build();
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
