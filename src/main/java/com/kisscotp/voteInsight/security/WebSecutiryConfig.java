package com.kisscotp.voteInsight.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class WebSecutiryConfig {
    
    @Autowired
    private DataSource dateasource;


    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
        .csrf((csrfConfig) ->
            csrfConfig.disable()
        )
        .cors((corsConfig) ->
            corsConfig.disable()
        )
        .authorizeHttpRequests(request -> request
        
            // .dispatcherTypeMatchers(DispatchType.FORWARD).permitAll()
            .requestMatchers("/**").permitAll()// 일단 모든 페이지 접근 가능하게 설정
            .anyRequest().authenticated()
        );
        return http.build();
    }
}
