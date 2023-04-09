package com.javarush.hibernate2.entity;

import java.util.Arrays;

public enum Rating {
    G("G"),
    PG("PG"),
    PG13("PG-13"),
    R("R"),
    NC17("NC-17"),
    NOT_SET("");

    private final String value;

    Rating(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static Rating getByValue(String value) {
        return Arrays.stream(Rating.values())
                .filter(f -> f.value.equals(value))
                .findAny().orElse(NOT_SET);
    }
}
