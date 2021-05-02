package com.epam.jwd.core_final.json.impl;

import com.epam.jwd.core_final.domain.Spaceship;
import com.epam.jwd.core_final.json.api.JSONWriter;
import com.epam.jwd.core_final.service.impl.SimpleSpaceshipService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.Collection;

public class SpaceshipsJSONWriterStrategy implements JSONWriter {

    private final Logger LOGGER = LoggerFactory.getLogger(SpaceshipsJSONWriterStrategy.class);

    private static SpaceshipsJSONWriterStrategy instance;

    private SpaceshipsJSONWriterStrategy() {
    }

    public static SpaceshipsJSONWriterStrategy getInstance() {
        if (instance == null) {
            instance = new SpaceshipsJSONWriterStrategy();
        }
        return instance;
    }

    @Override
    public void writeJSON(String dir) {
        Collection<Spaceship> spaceshipsList = SimpleSpaceshipService.getInstance().findAllSpaceships();
        File outputFile = new File(dir + File.separator + "spaceships.json");
        try {
            SimpleJSONWriter.OBJECT_MAPPER.writeValue(outputFile, spaceshipsList);
        } catch (IOException e) {
            LOGGER.error("Problem with output info about spaceships. Check for the output folder: " + dir);
        }
        LOGGER.info("Output info about spaceships to " + outputFile.getName());
    }

}
