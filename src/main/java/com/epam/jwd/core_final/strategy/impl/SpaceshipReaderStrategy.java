package com.epam.jwd.core_final.strategy.impl;

import com.epam.jwd.core_final.domain.Role;
import com.epam.jwd.core_final.domain.Spaceship;
import com.epam.jwd.core_final.exception.DuplicateEntityException;
import com.epam.jwd.core_final.exception.UnknownEntityException;
import com.epam.jwd.core_final.factory.impl.SpaceshipFactory;
import com.epam.jwd.core_final.service.SpaceshipService;
import com.epam.jwd.core_final.service.impl.SimpleSpaceshipService;
import com.epam.jwd.core_final.strategy.api.ReadFileStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class SpaceshipReaderStrategy implements ReadFileStrategy {

    private final SpaceshipFactory FACTORY = SpaceshipFactory.getInstance();
    private final SpaceshipService SERVICE = SimpleSpaceshipService.getInstance();
    private final Logger LOGGER = LoggerFactory.getLogger(SpaceshipReaderStrategy.class);
    private static SpaceshipReaderStrategy instance;

    private SpaceshipReaderStrategy() {
    }

    public static SpaceshipReaderStrategy getInstance() {
        if (instance == null) {
            instance = new SpaceshipReaderStrategy();
        }
        return instance;
    }

    @Override
    public void readFile(String path, String filename) throws FileNotFoundException {
        String filePath = "src" + File.separator +
                "main" + File.separator + "resources" +
                File.separator + path + File.separator + filename;
        Scanner scanner = new Scanner(new File(filePath));
        readSpaceships(scanner);
    }

    private void readSpaceships(Scanner scanner) {
        while (scanner.hasNextLine()) {
            String nextLine = scanner.nextLine();
            if (nextLine.charAt(0) != '#') {
                createSpaceship(nextLine.split(";"));
            }
        }
    }

    private Map<Role, Short> parseLineToMap(String line) {
        Map<Role, Short> crew = new HashMap<>();
        line = line.substring(1, line.length() - 1);
        for (String pairInLine : line.split(",")) {
            String[] numOfRole = pairInLine.split(":");
            crew.put(Role.resolveRoleById(Long.parseLong(numOfRole[0])),
                    Short.parseShort(numOfRole[1]));
        }
        return crew;
    }

    private void createSpaceship(String[] spaceshipData) {
        try {
            Spaceship spaceship = FACTORY.create(
                    spaceshipData[0],
                    Long.parseLong(spaceshipData[1]),
                    parseLineToMap(spaceshipData[2]));

            SERVICE.createSpaceship(spaceship);
        } catch (DuplicateEntityException | UnknownEntityException e) {
            LOGGER.error(e.getMessage());
        }
    }
}
