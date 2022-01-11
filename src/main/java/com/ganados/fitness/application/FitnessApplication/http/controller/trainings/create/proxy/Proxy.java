package com.ganados.fitness.application.FitnessApplication.http.controller.trainings.create.proxy;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

import com.ganados.fitness.application.FitnessApplication.http.controller.trainings.create.exception.NotANumberException;
import com.ganados.fitness.application.FitnessApplication.http.controller.trainings.create.paramters.SaveExerciseParameters;
import com.ganados.fitness.application.FitnessApplication.model.training.Training;
import com.ganados.fitness.application.FitnessApplication.model.training.details.TrainingDetails;
import com.ganados.fitness.application.FitnessApplication.model.training.exercises.Exercise;
import com.ganados.fitness.application.FitnessApplication.model.training.exercises.series.Series;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.textfield.TextField;

import lombok.extern.java.Log;

@Log
public class Proxy {

    public static Training prepare(final LinkedHashMap<FormLayout, SaveExerciseParameters> exerciseInfo, final String date) throws NotANumberException {
        Set<Exercise> exercises = new HashSet<>();
        for (FormLayout formLayout : exerciseInfo.keySet()) {
            Exercise exercise = getExercise(exerciseInfo.get(formLayout));
            exercises.add(exercise);
        }

        return Training.builder()
                .date(date)
                .trainingDetails(new TrainingDetails(exercises))
                .build();
    }

    private static Exercise getExercise(final SaveExerciseParameters saveExerciseParameters) throws NotANumberException {
        return Exercise.builder()
                .name(saveExerciseParameters.getName().getValue())
                .series(getSeries(saveExerciseParameters.getReps(), saveExerciseParameters.getWeights()))
                .build();
    }

    private static List<Series> getSeries(final List<TextField> reps, final List<TextField> weights) throws NotANumberException {
        List<Series> series = new ArrayList<>();
        for (int i = 0; i < reps.size(); i++) {
            series.add(getSeries(reps.get(i), weights.get(i)));
        }
        return series;
    }

    private static Series getSeries(final TextField rep, final TextField weight) throws NotANumberException {
        return Series.builder()
                .reps(parseInt(rep.getValue()))
                .weight(parseDouble(weight.getValue()))
                .build();
    }

    private static int parseInt(final String number) throws NotANumberException {
        int intNumber = 0;
        try {
            intNumber = Integer.parseInt(number.trim());
        } catch (final NumberFormatException numberFormatException) {
            log.warning("Wrong number");
            throw new NotANumberException("Inserted char is not a number");
        }
        return intNumber;
    }

    private static double parseDouble(final String number) throws NotANumberException {
        double doubleNumber = 0;
        try {
            doubleNumber = Integer.parseInt(number.trim());
        } catch (final NumberFormatException numberFormatException) {
            log.warning("Wrong number");
            throw new NotANumberException("Inserted char is not a number");
        }
        return doubleNumber;
    }
}
