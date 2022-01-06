package com.ganados.fitness.application.FitnessApplication.database.service;

import java.util.List;
import javax.transaction.Transactional;

import com.ganados.fitness.application.FitnessApplication.database.mapper.TrainingsMapper;
import com.ganados.fitness.application.FitnessApplication.model.training.Training;
import com.ganados.fitness.application.FitnessApplication.model.user.User;
import com.ganados.fitness.application.FitnessApplication.repositories.details.TrainingDetailsRepository;
import com.ganados.fitness.application.FitnessApplication.repositories.exercise.ExerciseRepository;
import com.ganados.fitness.application.FitnessApplication.repositories.series.SeriesRepository;
import com.ganados.fitness.application.FitnessApplication.repositories.training.TrainingRepository;
import com.ganados.fitness.application.FitnessApplication.repositories.user.UserRepository;
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

    private final UserRepository userRepository;

    @Transactional
    public List<Training> getAllTrainings() {
        Iterable<Training> all = trainingRepository.findAll();
        log.info("Getting trainings from database...");
        return TrainingsMapper.toTrainingList(all);
    }

    @Transactional
    public void saveTraining(final Training training) {
        this.trainingRepository.save(training);
        log.info("Training saved successfully");
    }

    @Transactional
    public boolean getTraining(final String date) {
        return trainingRepository.findTrainingByDate(date) == null;
    }

    @Transactional
    public void saveTrainings(final List<Training> trainings) {
        this.trainingRepository.saveAll(trainings);
        log.info("Trainings saved successfully");
    }

    @Transactional
    public void saveUser(final User user) {
        this.userRepository.save(user);
        log.info("User saved successfully");
    }

    @Transactional
    public User getUser(final String email) {
        log.info("Getting user from database...");
        return this.userRepository.findByEmail(email);
    }

}
