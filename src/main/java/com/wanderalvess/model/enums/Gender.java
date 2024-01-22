package com.wanderalvess.model.enums;

//Gênero
public enum Gender {
    MALE("Masculino"),
    FEMALE("Feminino");

    private final String description;

    Gender(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}

