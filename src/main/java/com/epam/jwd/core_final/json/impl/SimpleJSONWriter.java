package com.epam.jwd.core_final.json.impl;

import com.epam.jwd.core_final.json.api.JSONWriter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class SimpleJSONWriter implements JSONWriter {

    static final ObjectMapper OBJECT_MAPPER = new ObjectMapper()
            .configure(SerializationFeature.INDENT_OUTPUT, true);

    private final JSONWriter strategy;

    public SimpleJSONWriter(JSONWriter strategy) {
        this.strategy = strategy;
    }

    @Override
    public void writeJSON(String dir) {
        strategy.writeJSON(dir);
    }

}
