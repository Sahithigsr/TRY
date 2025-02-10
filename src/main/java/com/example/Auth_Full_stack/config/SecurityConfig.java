package com.example.Auth_Full_stack.config;

import com.example.Auth_Full_stack.filter.AuthTokenFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Autowired
    private AuthTokenFilter authTokenFilter;

    @Bean
    AuthTokenFilter getAuthTokenFilter()
    {
        return new AuthTokenFilter();
    }

    @Bean
    SecurityFilterChain fun(HttpSecurity httpSecurity) throws Exception
    {
//        httpSecurity.authorizeHttpRequests(i->i.requestMatchers("/api/v1/login/**").permitAll().requestMatchers("/api/v1/add/**").authenticated().anyRequest().authenticated());
        httpSecurity.authorizeHttpRequests(i -> i
                .requestMatchers("/api/v1/login/**").permitAll()
                .requestMatchers("/api/v1/add/**").hasRole("USER")
                .anyRequest().authenticated());
        httpSecurity.csrf(AbstractHttpConfigurer::disable);
        httpSecurity.httpBasic(Customizer.withDefaults());
        httpSecurity.addFilterBefore(getAuthTokenFilter(), UsernamePasswordAuthenticationFilter.class);
        return httpSecurity.build();
    }



}
