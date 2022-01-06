package com.ganados.fitness.application.FitnessApplication.http.controller.trainings.all;

import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.ganados.fitness.application.FitnessApplication.http.controller.basic.BasicController;
import com.ganados.fitness.application.FitnessApplication.model.training.Training;
import com.ganados.fitness.application.FitnessApplication.model.training.details.TrainingDetails;
import com.ganados.fitness.application.FitnessApplication.model.training.exercises.Exercise;
import com.ganados.fitness.application.FitnessApplication.model.training.exercises.series.Series;
import com.vaadin.flow.component.accordion.Accordion;
import com.vaadin.flow.component.accordion.AccordionPanel;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

import lombok.AllArgsConstructor;

@AllArgsConstructor(staticName = "of")
public class ShowAllController extends BasicController {

    public FlexLayout printPlans(final List<Training> trainings) {
        FlexLayout flexLayout = new FlexLayout();
        for (Training training : trainings) {
            flexLayout.add(planStructure(training));
        }
        flexLayout.setFlexWrap(FlexLayout.FlexWrap.WRAP);
        flexLayout.setJustifyContentMode(FlexComponent.JustifyContentMode.EVENLY);
        flexLayout.setAlignContent(FlexLayout.ContentAlignment.CENTER);

        return flexLayout;
    }

    private Accordion planStructure(final Training training) {
        Accordion mainAccordion = new Accordion();
        String trainingId = "Training " + training.getId().toString();
        Span trainingDate = new Span(training.getDate().replace("T", " "));

        TrainingDetails trainingDetails = training.getTrainingDetails();

        VerticalLayout trainingDateLayout = new VerticalLayout(trainingDate);
        trainingDateLayout.setSpacing(false);
        trainingDateLayout.setPadding(false);

        mainAccordion.add(trainingId, trainingDateLayout);

        List<Exercise> exercises = sortExercises(trainingDetails.getExercises());

        for (Exercise exercise : exercises) {
            String name = exercise.getName();
            VerticalLayout tempReps = getExercisesLayout(exercise);

            AccordionPanel temp = new AccordionPanel(name, getSeriesLayout(tempReps));
            temp.getStyle().set("margin", "1.2em");
            mainAccordion.add(temp);
        }
        mainAccordion.getStyle().set("margin", "1em");
        mainAccordion.close();
        return mainAccordion;
    }

    private List<Exercise> sortExercises(final Set<Exercise> exercises) {
        return exercises.stream().sorted(Comparator.comparing(Exercise::getOrderNo)).collect(Collectors.toList());
    }

    private VerticalLayout getExercisesLayout(final Exercise exercise) {
        VerticalLayout tempReps = new VerticalLayout();
        tempReps.setPadding(false);
        tempReps.setSpacing(false);
        for (Series series : exercise.getSeries()) {
            Span repeats = new Span("Repeats: " + series.getReps());
            Span weights = new Span("Weights: " + series.getWeight() + "kg");
            tempReps.add(repeats, weights, getEmptyLabel());
        }
        return tempReps;
    }

    private VerticalLayout getSeriesLayout(VerticalLayout tempReps) {
        VerticalLayout tempSeries = new VerticalLayout(tempReps);
        tempSeries.setPadding(false);
        tempSeries.setSpacing(false);
        return tempSeries;
    }

    private Label getEmptyLabel() {
        Label label = new Label("");
        label.setHeight("1em");
        return label;
    }
}