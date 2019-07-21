package com.alexislavie.coding.assignment.becare.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.util.Map;

@SuppressWarnings("rawtypes")
public abstract class MapToArrayOfObjectsWithSingleAttributeSerializer<V> extends JsonSerializer<Map> {

    protected abstract void writeJsonType(JsonGenerator gen, String fieldName, V value) throws IOException;

    @Override
    @SuppressWarnings("unchecked")
    public void serialize(Map value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeStartArray();

        Map<String, V> map = (Map<String, V>) value;
        for (Map.Entry<String, V> entry : map.entrySet()) {
            gen.writeStartObject();
            writeJsonType(gen, entry.getKey(), entry.getValue());
            gen.writeEndObject();
        }

        gen.writeEndArray();
    }

    @Override
    public Class<Map> handledType() {
        return Map.class;
    }
}
