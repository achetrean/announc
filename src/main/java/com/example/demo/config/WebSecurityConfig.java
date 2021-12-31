package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@EnableGlobalMethodSecurity(jsr250Enabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private static final String ADMIN_ENDPOINT = "/api/admin/**";
    private static final String AUTHENTICATION_ENDPOINT = "/api/auth/**";
    private static final String[] SWAGGER_ENDPOINTS = {"/swagger-resources/**", "/swagger-ui/",
            "/swagger-ui/**", "/v2/api-docs", "/webjars/**"};
    private static final String SPRING_ACTUATOR = "/api/actuator/**";

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .httpBasic().disable()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers(AUTHENTICATION_ENDPOINT).permitAll()
                .antMatchers(SWAGGER_ENDPOINTS).permitAll()
                .antMatchers(ADMIN_ENDPOINT).hasRole("ADMIN")
                .antMatchers(SPRING_ACTUATOR).hasRole("ADMIN")
                .antMatchers(SPRING_ACTUATOR).hasRole("SYSADMIN")
                .anyRequest().authenticated();
    }
}