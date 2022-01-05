package com.ganados.fitness.application.FitnessApplication.http.controller.trainings.create.proxy;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.ganados.fitness.application.FitnessApplication.model.training.Training;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.formlayout.FormLayout;

import lombok.extern.java.Log;

import static java.util.Map.Entry.comparingByKey;

@Log
public class Proxy {

    public static List<Training> prepare(final List<Component> componentList) {
        Map<Integer, FormLayout> formLayouts = new LinkedHashMap<>();
        for (Component component : componentList) {
            if (component instanceof FormLayout) {
                formLayouts.put(parseInt(component.getId().get()), (FormLayout) component);
            }
        }
        LinkedHashMap<Integer, FormLayout> sortedFormLayouts = sortMap(formLayouts);

        for(int key : sortedFormLayouts.keySet()) {
            FormLayout formLayout = sortedFormLayouts.get(key);

        }

        return null;
    }

    private static int parseInt(final String id) {
        int intId = 0;
        try {
            intId = Integer.parseInt(id);
        } catch (final NumberFormatException numberFormatException) {
            log.warning("Wrong form id");
        }
        return intId;
    }

    private static LinkedHashMap<Integer, FormLayout> sortMap(Map<Integer, FormLayout> formLayouts) {
        return formLayouts.entrySet()
                .stream()
                .sorted(comparingByKey())
                .collect(
                        Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2,
                                LinkedHashMap::new));
    }
}
