package com.ganados.fitness.application.FitnessApplication.repositories.training;

import com.ganados.fitness.application.FitnessApplication.model.training.Training;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrainingRepository extends CrudRepository<Training, Long> {

    Training findTrainingByDate(final String date);
}
