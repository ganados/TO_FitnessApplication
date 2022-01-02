package com.ganados.fitness.application.FitnessApplication.model.training.details;

import java.util.Objects;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.ganados.fitness.application.FitnessApplication.model.training.base.BaseEntity;
import com.ganados.fitness.application.FitnessApplication.model.training.exercises.Exercise;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "training_details")
public class TrainingDetails extends BaseEntity {

    @Builder
    public TrainingDetails(final Set<Exercise> exercises) {
        super();
        this.exercises = exercises;
    }

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Exercise> exercises;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TrainingDetails that = (TrainingDetails) o;
        return exercises.equals(that.exercises);
    }

    @Override
    public int hashCode() {
        return Objects.hash(exercises);
    }
}
