package com.kisscotp.voteInsight.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class WebSecutiryConfig  {
    
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
        );

        http
        .authorizeHttpRequests(request -> request
            .requestMatchers("/**").permitAll()// 일단 모든 경로 접근 가능하게 설정
            .anyRequest().authenticated()
        );

        http
        .formLogin(login -> login
        .loginPage("/loginForm")
        .loginProcessingUrl("/user/login")
        .usernameParameter("studentid")
        .passwordParameter("password")
        .defaultSuccessUrl("/",true)
        .permitAll()
        );
        
        http
        .logout(logout -> logout
        .logoutUrl("/logout")
        .logoutSuccessUrl("/")
        );

        return http.build();
    }

    // 패스워드 인코더
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
