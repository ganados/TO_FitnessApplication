package com.ganados.fitness.application.FitnessApplication.configuration.controllers;

import com.ganados.fitness.application.FitnessApplication.database.service.DatabaseService;
import com.ganados.fitness.application.FitnessApplication.http.controller.statistics.StatisticsController;
import com.ganados.fitness.application.FitnessApplication.http.controller.trainings.all.ShowAllController;
import com.ganados.fitness.application.FitnessApplication.http.controller.trainings.create.CreateTrainingController;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ControllersConfiguration {

    @Bean
    public ShowAllController showAllController() {
        return ShowAllController.of();
    }


    @Bean
    public CreateTrainingController createTrainingController(final DatabaseService databaseService) {
        return CreateTrainingController.of(databaseService);
    }

    @Bean
    public StatisticsController statisticsController(final DatabaseService databaseService) {
        return StatisticsController.of(databaseService);
    }
}
