package com.ganados.fitness.application.FitnessApplication.http.gui.trainings.all;

import com.ganados.fitness.application.FitnessApplication.database.provider.DatabaseProvider;
import com.ganados.fitness.application.FitnessApplication.database.service.DatabaseService;
import com.ganados.fitness.application.FitnessApplication.http.controller.trainings.all.ShowAllController;
import com.ganados.fitness.application.FitnessApplication.http.gui.basic.BasicView;
import com.ganados.fitness.application.FitnessApplication.model.training.Training;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

import static com.ganados.fitness.application.FitnessApplication.http.utilities.HttpConstants.SHOW_ALL_TRAININGS_PATH;

@Route(SHOW_ALL_TRAININGS_PATH)
public class Trainings extends VerticalLayout {

    private final DatabaseService databaseService;
    private final ShowAllController showAllController;

    public Trainings(final DatabaseService service, final ShowAllController showAllController) {
        this.databaseService = service;
        this.showAllController = showAllController;

        add(BasicView.getHelloLabel("All plans"));

        // TODO : REMOVE
        for (Training training : DatabaseProvider.readAllTrainings()) {
            this.databaseService.saveTraining(training);
        }
        // TODO : REMOVE

        FlexLayout flexLayout = this.showAllController.printPlans(this.databaseService.getAllTrainings());

        add(flexLayout);
        add(this.showAllController.backToMenu());
    }


}
