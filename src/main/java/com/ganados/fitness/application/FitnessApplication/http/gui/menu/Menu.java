package com.ganados.fitness.application.FitnessApplication.http.gui.menu;

import com.ganados.fitness.application.FitnessApplication.http.gui.trainings.all.Trainings;
import com.ganados.fitness.application.FitnessApplication.http.security.service.SecurityService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;

import static com.ganados.fitness.application.FitnessApplication.http.utilities.HttpConstants.MENU_PATH;

@Route(MENU_PATH)
public class Menu extends VerticalLayout {

    private final SecurityService securityService;

    public Menu(SecurityService service) {
        this.securityService = service;
        header();
        toPlans();

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

    private void toPlans() {
        Button button = new Button("Show trainings");
        RouterLink routerLink = new RouterLink("", Trainings.class);
        routerLink.getElement().appendChild(button.getElement());
        add(routerLink);
    }
}