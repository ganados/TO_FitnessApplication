package com.ganados.fitness.application.FitnessApplication.http.controller.trainings.create;

import java.text.ParseException;

import com.ganados.fitness.application.FitnessApplication.database.service.DatabaseService;
import com.ganados.fitness.application.FitnessApplication.http.controller.basic.BasicController;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.textfield.TextField;

import lombok.AllArgsConstructor;

@AllArgsConstructor(staticName = "of")
public class CreateTrainingController extends BasicController {

    private final DatabaseService databaseService;
    private static int exerciseCounter = 0;

    public FormLayout makeDataForm() throws ParseException {
        TextField exerciseName = new TextField("Exercise name");
        TextField reps = new TextField("Exercise reps");
        TextField weight = new TextField("Reps weight");
        Button removeExerciseButton = new Button("Remove this exercise");

        removeExerciseButton.addClickListener(e -> {
                    exerciseName.setValue("");
                    reps.setValue("");
                    weight.setValue("");
                    Notification.show("Exercise removed");
                }
        );

        FormLayout formLayout = new FormLayout();
        formLayout.setId(String.valueOf(exerciseCounter++));
        formLayout.add(
                exerciseName,
                reps,
                weight,
                removeExerciseButton
        );
        formLayout.setColspan(exerciseName, 3);
        formLayout.setColspan(reps, 3);
        formLayout.setColspan(weight, 3);

        return formLayout;
    }
}