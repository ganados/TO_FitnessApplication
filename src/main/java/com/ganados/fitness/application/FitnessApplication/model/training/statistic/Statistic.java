package com.ganados.fitness.application.FitnessApplication.model.training.statistic;

import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor(staticName = "of")
public class Statistic {

    private Map<String, Double> stats;
}