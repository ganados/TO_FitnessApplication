package com.ganados.fitness.application.FitnessApplication.http.security.config;

import com.ganados.fitness.application.FitnessApplication.http.security.cache.CustomRequestCache;
import com.ganados.fitness.application.FitnessApplication.http.security.utils.SecurityUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import static com.ganados.fitness.application.FitnessApplication.http.security.utils.SecurityConstants.LOGIN_FAILURE_URL;
import static com.ganados.fitness.application.FitnessApplication.http.security.utils.SecurityConstants.LOGIN_PROCESSING_URL;
import static com.ganados.fitness.application.FitnessApplication.http.security.utils.SecurityConstants.LOGIN_SUCCESSFUL_URL;
import static com.ganados.fitness.application.FitnessApplication.http.security.utils.SecurityConstants.LOGIN_URL;
import static com.ganados.fitness.application.FitnessApplication.http.security.utils.SecurityConstants.LOGOUT_SUCCESS_URL;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf().disable()
                .headers().frameOptions().disable()
                .and()
                .requestCache().requestCache(new CustomRequestCache())
                .and().authorizeRequests()
                .requestMatchers(SecurityUtils::isFrameworkInternalRequest).permitAll()
                .anyRequest().authenticated()
                .and().formLogin()
                .loginPage(LOGIN_URL).permitAll()
                .loginProcessingUrl(LOGIN_PROCESSING_URL)
                .defaultSuccessUrl(LOGIN_SUCCESSFUL_URL)
                .failureUrl(LOGIN_FAILURE_URL)
                .and().logout().logoutSuccessUrl(LOGOUT_SUCCESS_URL);
    }

    @Bean
    @Override
    public UserDetailsService userDetailsService() {
        // TODO: pobieranie danych z bazy
        UserDetails user = User.withUsername("user")
                .password("{noop}userpass")
                .roles("USER")
                .build();

        return new InMemoryUserDetailsManager(user);
    }

    @Override
    public void configure(WebSecurity webSecurity) {
        webSecurity.ignoring().antMatchers(
                "/VAADIN/**",
                "/favicon.ico",
                "/robots.txt",
                "/manifest.webmanifest",
                "/sw.js",
                "/offline.html",
                "/icons/**",
                "/images/**",
                "/styles/**",
                "/h2-console/**");
    }
}