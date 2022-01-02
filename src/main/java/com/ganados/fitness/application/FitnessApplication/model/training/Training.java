package com.ganados.fitness.application.FitnessApplication.model.training;

import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.ganados.fitness.application.FitnessApplication.model.training.base.BaseEntity;
import com.ganados.fitness.application.FitnessApplication.model.training.details.TrainingDetails;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "training")
public class Training extends BaseEntity {

    @Builder
    public Training(final String dateTime, final TrainingDetails trainingDetails) {
        super();
        this.dateTime = dateTime;
        this.trainingDetails = trainingDetails;
    }

    private String dateTime;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "training_details_id")
    private TrainingDetails trainingDetails;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Training training = (Training) o;
        return dateTime.equals(training.dateTime) && trainingDetails.equals(training.trainingDetails);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dateTime, trainingDetails);
    }
}