package com.ganados.fitness.application.FitnessApplication.http.controller.trainings.create.paramters;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor(staticName = "of")
public class SaveExerciseParameters {
    private String name;
    private List<Integer> reps;
    private List<Double> weights;

    public void addRep(final int rep) {
        this.reps.add(rep);
    }

    public void addWeight(final double weight) {
        this.weights.add(weight);
    }

    public void addExerciseElements(final int rep, final double weight) {
        addRep(rep);
        addWeight(weight);
    }
}
