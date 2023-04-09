package com.javarush.hibernate2.converter;

import jakarta.persistence.AttributeConverter;

import java.time.Year;

import static java.util.Objects.isNull;

public class YearConverter implements AttributeConverter<Year, Short> {
    @Override
    public Short convertToDatabaseColumn(Year attribute) {
        if (isNull(attribute)) {
            return null;
        }
        return (short) attribute.getValue();
    }

    @Override
    public Year convertToEntityAttribute(Short dbData) {
        if (isNull(dbData)) {
            return null;
        }
        return Year.of(dbData);
    }
}
