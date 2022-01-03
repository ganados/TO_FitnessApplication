package com.ganados.fitness.application.FitnessApplication.repositories.details;

import com.ganados.fitness.application.FitnessApplication.model.training.details.TrainingDetails;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrainingDetailsRepository extends CrudRepository<TrainingDetails, Long> {
}
