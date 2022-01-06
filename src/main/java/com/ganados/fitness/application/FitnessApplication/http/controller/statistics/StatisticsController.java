package com.ganados.fitness.application.FitnessApplication.http.controller.statistics;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.ganados.fitness.application.FitnessApplication.database.service.DatabaseService;
import com.ganados.fitness.application.FitnessApplication.http.controller.basic.BasicController;
import com.ganados.fitness.application.FitnessApplication.model.training.Training;
import com.ganados.fitness.application.FitnessApplication.model.training.exercises.Exercise;
import com.ganados.fitness.application.FitnessApplication.model.training.exercises.series.Series;
import com.ganados.fitness.application.FitnessApplication.model.training.statistic.Statistic;

import lombok.AllArgsConstructor;

@AllArgsConstructor(staticName = "of")
public class StatisticsController extends BasicController {

    private final DatabaseService databaseService;

    public Map<String, Statistic> getStatistics() {
        List<Training> allTrainings = this.databaseService.getAllTrainings();
        return getDataForChart(allTrainings);
    }

    private Map<String, Statistic> getDataForChart(final List<Training> trainings) {
        List<String> names = getUniqueExercises(trainings);
        Map<String, Statistic> data = new LinkedHashMap<>();
        for (String name : names) {
            data.put(name, getStatsForExercise(name, trainings));
        }
        return data;
    }


    private List<String> getUniqueExercises(final List<Training> trainings) {
        List<String> names = new ArrayList<>();
        for (Training training : trainings) {
            for (Exercise exercise : training.getTrainingDetails().getExercises()) {
                if (!names.contains(exercise.getName())) {
                    names.add(exercise.getName());
                }
            }
        }
        return names;
    }

    private Statistic getStatsForExercise(final String name, final List<Training> trainings) {
        Map<String, Double> dateExercises = new LinkedHashMap<>();
        for (Training training : trainings) {
            Exercise exercise = getExercise(training.getTrainingDetails().getExercises(), name);
            if (exercise != null) {
                dateExercises.put(training.getDate(), getExerciseAvg(exercise));
            }
        }
        return Statistic.of(dateExercises);
    }

    private Exercise getExercise(final Set<Exercise> exercises, final String name) {
        for (Exercise exercise : exercises) {
            if (exercise.getName().equals(name)) {
                return exercise;
            }
        }
        return null;
    }

    private double getExerciseAvg(final Exercise exercise) {
        double weightCount = 0;

        for (Series series : exercise.getSeries()) {
            weightCount += series.getWeight();
        }
        return weightCount / exercise.getSeries().size();
    }
}
