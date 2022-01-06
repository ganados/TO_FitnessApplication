package com.ganados.fitness.application.FitnessApplication.http.controller.trainings.create;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import com.ganados.fitness.application.FitnessApplication.database.service.DatabaseService;
import com.ganados.fitness.application.FitnessApplication.http.controller.basic.BasicController;
import com.ganados.fitness.application.FitnessApplication.http.controller.trainings.create.paramters.SaveExerciseParameters;
import com.ganados.fitness.application.FitnessApplication.model.training.Training;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.textfield.TextField;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.java.Log;

@Log
@Getter
@AllArgsConstructor(staticName = "of")
public class CreateTrainingController extends BasicController {

    private final DatabaseService databaseService;
    private static int exerciseCounter = 0;

    private LinkedHashMap<FormLayout, SaveExerciseParameters> exerciseInfo;

    public FormLayout makeDataForm() throws ParseException {
        TextField exerciseName = new TextField("Exercise name");

        FormLayout formLayout = new FormLayout();
        formLayout.setId(String.valueOf(exerciseCounter++));

        Button removeExerciseButton = new Button("Remove this exercise");
        removeExerciseButton.addClickListener(buttonClickEvent -> removeExercise(formLayout));
        Button addSeries = new Button("Add reps and weight");
        addSeries.addClickListener(buttonClickEvent -> addFields(formLayout));

        createLists(formLayout, exerciseName);

        Label label = getEmptyLabel();

        formLayout.add(label, removeExerciseButton, addSeries, exerciseName);
        formLayout.setColspan(label, 2);
        formLayout.setColspan(exerciseName, 2);

        return formLayout;
    }

    public void saveExercise(final Training training) {
        databaseService.saveTraining(training);
    }

    private void removeExercise(FormLayout formLayout) {
        formLayout.removeAll();
        Notification.show("Exercise removed");
    }

    private void addFields(FormLayout formLayout) {
        TextField repsField = new TextField("Exercise reps");
        TextField weightField = new TextField("Reps weight");
        formLayout.add(repsField, weightField);
        addToLists(formLayout, repsField, weightField);
    }

    private void addToLists(FormLayout formLayout, TextField repsField, TextField weightField) {
        this.exerciseInfo.get(formLayout).addExerciseElements(repsField, weightField);
    }

    private void createLists(FormLayout formLayout, TextField exerciseName) {
        SaveExerciseParameters saveExerciseParameters = SaveExerciseParameters.builder()
                .name(exerciseName)
                .reps(new ArrayList<>())
                .weights(new ArrayList<>())
                .build();
        this.exerciseInfo.put(formLayout, saveExerciseParameters);
    }

    private Label getEmptyLabel() {
        Label label = new Label("");
        label.setHeight("2em");
        return label;
    }

    public void afterSave() {
        for(FormLayout formLayout : this.exerciseInfo.keySet()){
            formLayout.removeAll();
        }
        this.exerciseInfo.clear();
    }
}