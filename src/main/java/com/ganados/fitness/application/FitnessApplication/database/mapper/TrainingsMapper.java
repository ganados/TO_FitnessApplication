package com.ganados.fitness.application.FitnessApplication.database.mapper;

import java.util.ArrayList;
import java.util.List;

import com.ganados.fitness.application.FitnessApplication.model.training.Training;

import lombok.extern.java.Log;

@Log
public class TrainingsMapper {

    public static List<Training> toTrainingList(Iterable<Training> trainings) {
        List<Training> trainingList = new ArrayList<>();
        for (Training training : trainings) {
            trainingList.add(training);
        }
        log.info("Mapping to list");
        return trainingList;
    }
}
