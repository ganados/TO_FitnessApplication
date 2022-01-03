package com.ganados.fitness.application.FitnessApplication.repositories.exercise;

import com.ganados.fitness.application.FitnessApplication.model.training.exercises.Exercise;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExerciseRepository extends CrudRepository<Exercise, Long> {
}
