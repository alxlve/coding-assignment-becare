package com.alexislavie.coding.assignment.becare.json;

import com.fasterxml.jackson.core.JsonGenerator;

import java.io.IOException;

public class MeasuresSerializer extends MapToArrayOfObjectsWithSingleAttributeSerializer<Double> {

    @Override
    protected void writeJsonType(JsonGenerator gen, String fieldName, Double value) throws IOException {
        gen.writeNumberField(fieldName, value);
    }
}
