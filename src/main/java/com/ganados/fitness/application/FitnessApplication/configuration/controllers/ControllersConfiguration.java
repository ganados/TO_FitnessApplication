package com.ganados.fitness.application.FitnessApplication.configuration.controllers;

import com.ganados.fitness.application.FitnessApplication.http.controller.all.ShowAllController;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ControllersConfiguration {

    @Bean
    public ShowAllController showAllController() {
        return ShowAllController.of();
    }
}
