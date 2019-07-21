package com.alexislavie.coding.assignment.becare.json;

import com.fasterxml.jackson.databind.JsonNode;

public class MeasuresDeserializer extends ArrayOfObjectsWithSingleAttributeToMapDeserializer<Double> {

    @Override
    protected Double asParameterizedType(JsonNode jsonNode) {
        return jsonNode.asDouble();
    }

    @Override
    protected String parameterizedClassCanonicalName() {
        return Double.class.getCanonicalName();
    }
}
