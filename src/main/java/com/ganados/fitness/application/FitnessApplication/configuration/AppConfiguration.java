package com.ganados.fitness.application.FitnessApplication.configuration;

import com.ganados.fitness.application.FitnessApplication.configuration.controllers.ControllersConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import lombok.AllArgsConstructor;

@Configuration
@Import({
        ControllersConfiguration.class
})
@AllArgsConstructor
public class AppConfiguration {
}
