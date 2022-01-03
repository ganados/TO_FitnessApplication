package com.ganados.fitness.application.FitnessApplication.repositories.series;

import com.ganados.fitness.application.FitnessApplication.model.training.exercises.series.Series;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SeriesRepository extends CrudRepository<Series, Long> {
}
