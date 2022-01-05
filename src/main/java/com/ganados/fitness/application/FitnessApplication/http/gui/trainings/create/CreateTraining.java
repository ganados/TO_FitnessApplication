package com.ganados.fitness.application.FitnessApplication.http.gui.trainings.create;


import java.text.ParseException;
import java.time.LocalDateTime;

import com.ganados.fitness.application.FitnessApplication.http.controller.trainings.create.CreateTrainingController;
import com.ganados.fitness.application.FitnessApplication.http.gui.basic.BasicView;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

import static com.ganados.fitness.application.FitnessApplication.http.utilities.HttpConstants.CREATE_NEW_TRAINING_PATH;

@Route(CREATE_NEW_TRAINING_PATH)
public class CreateTraining extends VerticalLayout {

    private final CreateTrainingController createTrainingController;

    public CreateTraining(final CreateTrainingController controller) {

        this.createTrainingController = controller;

        Button addNewExerciseButton = new Button("add new Exercise");
        TextField firstName = new TextField("Date");
        firstName.setValue(LocalDateTime.now().toLocalDate().toString());

        add(BasicView.getHelloLabel("Create new training"));
        add(firstName);
        add(addNewExerciseButton);

        addNewExerciseButton.addClickListener(e -> {
                    try {
                        add(createTrainingController.makeDataForm());
                    } catch (ParseException parseException) {
                        parseException.printStackTrace();
                    }
                }
        );

        add(this.createTrainingController.backToMenu());
    }
}
