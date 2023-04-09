package com.javarush.hibernate2.entity;

import java.util.Arrays;

public enum Feature {
    TRAILERS("Trailers"),
    COMMENTARIES("Commentaries"),
    DELETED_SCENES("Deleted Scenes"),
    BEHIND_THE_SCENES("Behind the Scenes"),
    NOT_SET("");

    private final String value;

    Feature(String value) {
        this.value = value;
    }

    public static Feature getByValue(String value) {
       return Arrays.stream(Feature.values())
                .filter(f -> f.value.equals(value))
                .findAny().orElse(NOT_SET);
    }

    public String getValue() {
        return value;
    }
}
