package com.ganados.fitness.application.FitnessApplication.database.service;

import java.util.List;
import javax.transaction.Transactional;

import com.ganados.fitness.application.FitnessApplication.database.mapper.TrainingsMapper;
import com.ganados.fitness.application.FitnessApplication.model.training.Training;
import com.ganados.fitness.application.FitnessApplication.repositories.details.TrainingDetailsRepository;
import com.ganados.fitness.application.FitnessApplication.repositories.exercise.ExerciseRepository;
import com.ganados.fitness.application.FitnessApplication.repositories.series.SeriesRepository;
import com.ganados.fitness.application.FitnessApplication.repositories.training.TrainingRepository;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.extern.java.Log;

@Log
@Service
@Transactional
@AllArgsConstructor
public class DatabaseService {

    private final TrainingRepository trainingRepository;
    private final TrainingDetailsRepository trainingDetailsRepository;
    private final ExerciseRepository exerciseRepository;
    private final SeriesRepository seriesRepository;

    @Transactional
    public List<Training> getAllTrainings() {
        Iterable<Training> all = trainingRepository.findAll();
        log.info("Reading from database...");
        return TrainingsMapper.toTrainingList(all);
    }

    @Transactional
    public void saveTraining(final Training training) {
        this.trainingRepository.save(training);
        log.info("Training saved successfully");
    }

    @Transactional
    public void saveTrainings(final List<Training> trainings) {
        this.trainingRepository.saveAll(trainings);
        log.info("Trainings saved successfully");
    }
}
