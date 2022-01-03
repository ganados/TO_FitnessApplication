package com.ganados.fitness.application.FitnessApplication.database.provider;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.fasterxml.jackson.dataformat.yaml.YAMLGenerator;
import com.ganados.fitness.application.FitnessApplication.model.training.Training;

public class DatabaseProvider {

    private static final String DATA_PATH = "src/main/resources/data/";
    private static final String[] TRAININGS_PATH = {
            "training1.yaml",
            "training2.yaml",
            "training3.yaml",
            "training4.yaml"
    };

    public static List<Training> readAllTrainings() {
        List<Training> trainings = new ArrayList<>();
        for (String path : TRAININGS_PATH) {
            trainings.add(readTraining(path));
        }
        return trainings;
    }

    public static void writeTraining(final Training training) {
        ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory().disable(YAMLGenerator.Feature.WRITE_DOC_START_MARKER));
        objectMapper.disable(SerializationFeature.WRITE_DATE_KEYS_AS_TIMESTAMPS);
        objectMapper.findAndRegisterModules();
        try {
            objectMapper.writeValue(new File(DATA_PATH + "outputTrainingData.yaml"), training);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Training readTraining(final String path) {
        ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());
        objectMapper.findAndRegisterModules();
        Training training = null;
        try {
            training = objectMapper.readValue(new File(DATA_PATH + path), Training.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return training;
    }
}
