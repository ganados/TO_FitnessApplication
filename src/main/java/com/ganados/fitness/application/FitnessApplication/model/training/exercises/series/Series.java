package com.ganados.fitness.application.FitnessApplication.model.training.exercises.series;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.ganados.fitness.application.FitnessApplication.model.training.base.BaseEntity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "series")
public class Series extends BaseEntity {

    @Builder
    public Series(final int reps, final double weight) {
        super();
        this.reps = reps;
        this.weight = weight;
    }

    private int reps;
    private double weight;

}

