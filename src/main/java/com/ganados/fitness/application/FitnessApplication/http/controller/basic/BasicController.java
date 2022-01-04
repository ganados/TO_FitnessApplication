package com.ganados.fitness.application.FitnessApplication.http.controller.basic;

import com.ganados.fitness.application.FitnessApplication.http.gui.menu.Menu;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.router.RouterLink;

public class BasicController {

    public RouterLink backToMenu() {
        Button button = new Button("Back to menu");
        RouterLink routerLink = new RouterLink("", Menu.class);
        routerLink.getElement().appendChild(button.getElement());
        return routerLink;
    }
}