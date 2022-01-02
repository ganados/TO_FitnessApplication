package com.ganados.fitness.application.FitnessApplication.model.training.exercises;

import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.ganados.fitness.application.FitnessApplication.model.training.base.BaseEntity;
import com.ganados.fitness.application.FitnessApplication.model.training.exercises.series.Series;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "exercise")
public class Exercise extends BaseEntity {

    private static int counter = 0;

    @Builder
    public Exercise(final List<Series> series) {
        super();
        this.series = series;
    }

    private String name;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Series> series;

    private int orderNo = counter++;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Exercise exercise = (Exercise) o;
        return name.equals(exercise.name) && series.equals(exercise.series);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, series);
    }
}
