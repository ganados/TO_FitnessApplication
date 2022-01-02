package com.ganados.fitness.application.FitnessApplication.http.gui.menu;

import com.ganados.fitness.application.FitnessApplication.http.security.service.SecurityService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route("menu")
public class Menu extends VerticalLayout {
    private final SecurityService securityService;

    private Button showPlans;
    private Button createNewPlan;
    private Button logout;

    public Menu(SecurityService service) {
        this.securityService = service;
        header();
        buttons();
    }

    private void header() {
        Label label = new Label("Select what you want to do?");
        add(label);
    }

    private void buttons() {
        this.showPlans = new Button("Show plan");
        this.createNewPlan = new Button("Create new plan");
        this.logout = new Button("Log out");

        this.logout.addClickListener(buttonClickEvent -> securityService.logout());

        add(showPlans);
        add(createNewPlan);
        add(logout);
    }

}