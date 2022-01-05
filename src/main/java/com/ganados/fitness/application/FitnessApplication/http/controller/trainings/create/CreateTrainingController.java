package com.ganados.fitness.application.FitnessApplication.http.controller.trainings.create;

import java.text.ParseException;
import java.util.List;

import com.ganados.fitness.application.FitnessApplication.database.service.DatabaseService;
import com.ganados.fitness.application.FitnessApplication.http.controller.basic.BasicController;
import com.ganados.fitness.application.FitnessApplication.http.controller.trainings.create.paramters.SaveExerciseParameters;
import com.ganados.fitness.application.FitnessApplication.model.training.Training;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.textfield.TextField;

import lombok.AllArgsConstructor;
import lombok.extern.java.Log;

@Log
@AllArgsConstructor(staticName = "of")
public class CreateTrainingController extends BasicController {

    private final DatabaseService databaseService;
    private static int exerciseCounter = 0;
    private static int seriesCounter = 0;

    private SaveExerciseParameters saveExerciseParameters;

    public FormLayout makeDataForm() throws ParseException {
        TextField exerciseName = new TextField("Exercise name");
        TextField reps = new TextField("Exercise reps");
        TextField weight = new TextField("Reps weight");
        Button removeExerciseButton = new Button("Remove this exercise");

        removeExerciseButton.addClickListener(buttonClickEvent -> {
                    exerciseName.setValue("");
                    reps.setValue("");
                    weight.setValue("");
                    Notification.show("Exercise removed");
                    // TODO: FIX
                }
        );

        FormLayout formLayout = new FormLayout();
        formLayout.setId(String.valueOf(exerciseCounter++));

        Button addSeries = new Button("Add reps and weight");
        addSeries.addClickListener(buttonClickEvent -> {
            addFields(formLayout);
        });

        formLayout.add(
                exerciseName,
                reps,
                weight,
                removeExerciseButton,
                addSeries
        );
        formLayout.setColspan(exerciseName, 2);
        formLayout.setColspan(reps, 2);
        formLayout.setColspan(weight, 2);

        return formLayout;
    }

    public void saveExercise(final List<Training> trainings) {
        databaseService.saveTrainings(trainings);
    }

    private void addFields(FormLayout formLayout) {
        TextField repsField = new TextField("Exercise reps");
        repsField.setId(String.valueOf(seriesCounter));
        TextField weightField = new TextField("Reps weight");
        weightField.setId(String.valueOf(seriesCounter++));
        formLayout.add(repsField, weightField);
    }

    private void addToLists(final TextField rep, final TextField weight) {
        this.saveExerciseParameters.setName();
    }
}