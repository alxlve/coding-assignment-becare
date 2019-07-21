package com.alexislavie.coding.assignment.becare.json;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public abstract class ArrayOfObjectsWithSingleAttributeToMapDeserializer<V> extends JsonDeserializer<Map<String, V>> {

    protected abstract V asParameterizedType(JsonNode jsonNode);

    protected abstract String parameterizedClassCanonicalName();

    @Override
    public Map<String, V> deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        Map<String, V> map = new HashMap<>();

        ObjectCodec objectCodec = p.getCodec();
        TreeNode treeNode = objectCodec.readTree(p);

        if (treeNode.isArray()) {
            for (JsonNode jsonNode : (ArrayNode) treeNode) {
                Iterator<Map.Entry<String, JsonNode>> iterator = jsonNode.fields();

                while (iterator.hasNext()) {
                    Map.Entry<String, JsonNode> element = iterator.next();

                    map.put(element.getKey(), asParameterizedType(element.getValue()));
                }
            }
        } else {
            throw new JsonMappingException(p,
                    "Cannot deserialize value of type "
                            + Map.class.getCanonicalName()
                            + "<" + String.class.getCanonicalName() + ", " + parameterizedClassCanonicalName() + ">"
                            + " from TreeNode \"" + treeNode + "\""
                            + ": Failed to deserialize JSON array"
            );
        }

        return map;
    }

    @Override
    public Class<Map> handledType() {
        return Map.class;
    }
}
