package com.smartlogi.model;

public enum StatutColis {
    PREPARATION("En préparation"),
    EN_TRANSIT("En transit"),
    LIVRE("Livré");

    private final String description;

    StatutColis(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}