package com.ganados.fitness.application.FitnessApplication.http.gui.statistics;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

import com.ganados.fitness.application.FitnessApplication.http.controller.statistics.StatisticsController;
import com.ganados.fitness.application.FitnessApplication.http.gui.basic.BasicView;
import com.ganados.fitness.application.FitnessApplication.model.training.statistic.Statistic;
import com.vaadin.flow.component.charts.Chart;
import com.vaadin.flow.component.charts.model.ChartType;
import com.vaadin.flow.component.charts.model.Configuration;
import com.vaadin.flow.component.charts.model.ListSeries;
import com.vaadin.flow.component.charts.model.XAxis;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

import static com.ganados.fitness.application.FitnessApplication.http.utilities.HttpConstants.STATISTICS_PATH;

@Route(STATISTICS_PATH)
public class Statistics extends VerticalLayout {

    private final StatisticsController statisticsController;

    public Statistics(final StatisticsController statisticsController) {
        this.statisticsController = statisticsController;
        add(BasicView.getHelloLabel("Statistics"));
        add(this.statisticsController.backToMenu());

        Map<String, Statistic> statistics = this.statisticsController.getStatistics();

        for (String name : statistics.keySet()) {
            add(getChartForExercise(name, statistics));
        }
    }

    private Chart getChartForExercise(final String name, final Map<String, Statistic> statistics) {
        Chart chart = new Chart(ChartType.LINE);
        chart.setWidthFull();
        Configuration configuration = chart.getConfiguration();

        XAxis xAxis = configuration.getxAxis();
        configureXaxis(statistics.get(name).getStats().keySet(), xAxis);
        configuration.getyAxis().setTitle("Avg(lifted weight) by series");

        configuration.setTitle(name);
        configuration.addSeries(getSeries(statistics.get(name).getStats().values(), name));
        chart.setWidthFull();

        return chart;
    }

    private ListSeries getSeries(final Collection<Double> values, final String name) {
        return new ListSeries(name, new ArrayList<>(values));
    }

    private void configureXaxis(final Set<String> names, XAxis xAxis) {
        names.forEach(xAxis::addCategory);
    }
}
