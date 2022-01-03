package com.ganados.fitness.application.FitnessApplication.http.gui.menu;

import java.util.List;

import com.ganados.fitness.application.FitnessApplication.database.provider.DatabaseProvider;
import com.ganados.fitness.application.FitnessApplication.database.service.DatabaseService;
import com.ganados.fitness.application.FitnessApplication.http.security.service.SecurityService;
import com.ganados.fitness.application.FitnessApplication.model.training.Training;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route("menu")
public class Menu extends VerticalLayout {

    private final SecurityService securityService;
    private final DatabaseService databaseService;


    public Menu(final SecurityService service, final DatabaseService databaseService) {
        this.securityService = service;
        this.databaseService = databaseService;
        header();

        // TODO: remove
        for (Training training : DatabaseProvider.readAllTrainings()) {
            this.databaseService.saveTraining(training);
        }
        List<Training> allTrainings = databaseService.getAllTrainings();
        // TODO: remove

        logOut();
    }

    private void header() {
        Label label = new Label("Select what you want to do?");
        add(label);
    }

    private void logOut() {
        Button logout = new Button("Log out");
        logout.addClickListener(buttonClickEvent -> securityService.logout());
        add(logout);
    }

}