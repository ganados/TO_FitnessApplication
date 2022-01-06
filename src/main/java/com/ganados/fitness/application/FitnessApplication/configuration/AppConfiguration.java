package com.ganados.fitness.application.FitnessApplication.configuration;

import com.ganados.fitness.application.FitnessApplication.configuration.controllers.ControllersConfiguration;
import com.ganados.fitness.application.FitnessApplication.database.service.DatabaseService;
import com.ganados.fitness.application.FitnessApplication.model.user.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import lombok.AllArgsConstructor;

@Configuration
@Import({
        ControllersConfiguration.class
})
@AllArgsConstructor
public class AppConfiguration {

    private final DatabaseService databaseService;

    @Bean
    public void loadUser() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        User user = User.builder()
                .email("user@example.com")
                .password(bCryptPasswordEncoder.encode("userpass"))
                .firstname("user")
                .lastname("example")
                .role("USER")
                .build();
        databaseService.saveUser(user);
    }

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
