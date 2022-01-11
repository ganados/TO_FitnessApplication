package com.ganados.fitness.application.FitnessApplication.http.controller.trainings.create.exception;

public class NotANumberException extends Exception {
    public NotANumberException(final String errorMessage) {
        super(errorMessage);
    }
}
