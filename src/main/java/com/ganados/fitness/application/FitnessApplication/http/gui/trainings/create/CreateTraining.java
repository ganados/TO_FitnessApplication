package com.ganados.fitness.application.FitnessApplication.http.gui.trainings.create;


import java.text.ParseException;
import java.time.LocalDateTime;

import com.ganados.fitness.application.FitnessApplication.http.controller.trainings.create.CreateTrainingController;
import com.ganados.fitness.application.FitnessApplication.http.controller.trainings.create.proxy.Proxy;
import com.ganados.fitness.application.FitnessApplication.http.gui.basic.BasicView;
import com.ganados.fitness.application.FitnessApplication.model.training.Training;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

import static com.ganados.fitness.application.FitnessApplication.http.utilities.HttpConstants.CREATE_NEW_TRAINING_PATH;

@Route(CREATE_NEW_TRAINING_PATH)
public class CreateTraining extends VerticalLayout {

    private final CreateTrainingController createTrainingController;

    public CreateTraining(final CreateTrainingController controller) {

        this.createTrainingController = controller;

        setAlignItems(Alignment.CENTER);
        TextField date = new TextField("Date");
        date.setValue(LocalDateTime.now().toLocalDate().toString());
        add(BasicView.getHelloLabel("Create new training"));
        add(date);

        addExerciseFormButton();

        Button saveNewExerciseButton = new Button("Save");
        add(saveNewExerciseButton);
        saveNewExerciseButton.addClickListener(buttonClickEvent -> {
            Training training = Proxy.prepare(createTrainingController.getExerciseInfo(), date.getValue());
            createTrainingController.saveExercise(training);
            Notification.show("Trainings saved");
            createTrainingController.afterSave();
        });


        add(this.createTrainingController.backToMenu());
    }

    private void addExerciseFormButton() {
        Button addNewExerciseFormButton = new Button("Add new exercise");
        add(addNewExerciseFormButton);
        addNewExerciseFormButton.addClickListener(e -> {
                    try {
                        add(createTrainingController.makeDataForm());
                    } catch (final ParseException parseException) {
                        parseException.printStackTrace();
                    }
                }
        );
    }
}
