package com.ganados.fitness.application.FitnessApplication.http.controller.trainings.create.paramters;

import java.util.List;

import com.vaadin.flow.component.textfield.TextField;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor(staticName = "of")
public class SaveExerciseParameters {
    private TextField name;
    private List<TextField> reps;
    private List<TextField> weights;

    public void addRep(final TextField rep) {
        this.reps.add(rep);
    }

    public void addWeight(final TextField weight) {
        this.weights.add(weight);
    }

    public void addExerciseElements(final TextField rep, final TextField weight) {
        addRep(rep);
        addWeight(weight);
    }
}
