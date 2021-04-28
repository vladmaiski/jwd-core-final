package com.epam.jwd.core_final.json.impl;

import com.epam.jwd.core_final.domain.FlightMission;
import com.epam.jwd.core_final.json.api.JSONWriter;
import com.epam.jwd.core_final.service.impl.SimpleMissionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.Collection;

public class MissionsJSONWriterStrategy implements JSONWriter {

    private final Logger LOGGER = LoggerFactory.getLogger(CrewMembersJSONWriterStrategy.class);

    private static MissionsJSONWriterStrategy instance;

    private MissionsJSONWriterStrategy() {
    }

    public static MissionsJSONWriterStrategy getInstance() {
        if (instance == null) {
            instance = new MissionsJSONWriterStrategy();
        }
        return instance;
    }

    @Override
    public void writeJSON(String dir) {
        Collection<FlightMission> missionsList = SimpleMissionService.getInstance().findAllMissions();
        for (FlightMission mission : missionsList) {
            try {
                SimpleJSONWriter.OBJECT_MAPPER.writeValue(new File("src" + File.separator + "main"
                        + File.separator + "resources" + File.separator + dir
                        + File.separator + mission.getName() + ".json"), mission);
            } catch (IOException e) {
                LOGGER.error("Problem with output info about all missions. Check for the output folder: " + dir);
            }
        }
        LOGGER.info("Output info about missions to " + dir);
    }

}
