package com.epam.jwd.core_final.json.impl;

import com.epam.jwd.core_final.domain.CrewMember;
import com.epam.jwd.core_final.json.api.JSONWriter;
import com.epam.jwd.core_final.service.impl.SimpleCrewService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.Collection;

public class CrewMembersJSONWriterStrategy implements JSONWriter {

    private final Logger LOGGER = LoggerFactory.getLogger(CrewMembersJSONWriterStrategy.class);

    private static CrewMembersJSONWriterStrategy instance;

    private CrewMembersJSONWriterStrategy() {
    }

    public static CrewMembersJSONWriterStrategy getInstance() {
        if (instance == null) {
            instance = new CrewMembersJSONWriterStrategy();
        }
        return instance;
    }

    @Override
    public void writeJSON(String dir) {
        Collection<CrewMember> crewMembersList = SimpleCrewService.getInstance().findAllCrewMembers();
        dir += (File.separator + "crewMembers.json");
        File outputFile = new File(dir);
        try {
            SimpleJSONWriter.OBJECT_MAPPER.writeValue(outputFile, crewMembersList);
        } catch (IOException e) {
            LOGGER.error("Problem with output info about crew members. Check for the output folder: " + dir);
        }
        LOGGER.info("Output info about crew members to " + outputFile.getName());
    }

}
