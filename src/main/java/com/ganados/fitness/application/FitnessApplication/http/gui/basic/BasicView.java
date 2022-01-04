package com.ganados.fitness.application.FitnessApplication.http.gui.basic;

import com.vaadin.flow.component.html.Label;

public class BasicView {

    public static Label getHelloLabel(final String name) {
        Label label = new Label(name);
        label.getStyle().set("background-color", "#1676F3");
        label.getStyle().set("color", "white");
        label.setWidthFull();
        label.getStyle().set("text-align", "center");
        label.getStyle().set("vertical-align", "text-middle");
        label.getStyle().set("border-radius", "5px");
        return label;
    }
}
