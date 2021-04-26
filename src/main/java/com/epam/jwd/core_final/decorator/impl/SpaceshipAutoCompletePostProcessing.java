package com.epam.jwd.core_final.decorator.impl;

import com.epam.jwd.core_final.criteria.SpaceshipCriteria;
import com.epam.jwd.core_final.decorator.api.EntityPostProcessor;
import com.epam.jwd.core_final.domain.FlightMission;
import com.epam.jwd.core_final.domain.Spaceship;
import com.epam.jwd.core_final.exception.AssignedOnMissionException;
import com.epam.jwd.core_final.factory.impl.SpaceshipFactory;
import com.epam.jwd.core_final.service.impl.SimpleSpaceshipService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class SpaceshipAutoCompletePostProcessing implements EntityPostProcessor<FlightMission> {

    private final Logger LOGGER = LoggerFactory.getLogger(SpaceshipFactory.class);
    private static SpaceshipAutoCompletePostProcessing instance;

    public static SpaceshipAutoCompletePostProcessing getInstance() {
        if (instance == null) {
            instance = new SpaceshipAutoCompletePostProcessing();
        }
        return instance;
    }

    @Override
    public FlightMission process(FlightMission object) {
        SimpleSpaceshipService spaceshipService = SimpleSpaceshipService.getInstance();
        List<Spaceship> spaceships = spaceshipService.findAllSpaceshipsByCriteria(SpaceshipCriteria.builder().readyForNextMission(true).build());
        Spaceship closestSpaceship = null;
        Long distance = object.getDistance();
        for (Spaceship currentSpaceship : spaceships) {
            if (currentSpaceship.getFlightDistance() >= distance) {
                if (closestSpaceship != null && closestSpaceship.getFlightDistance() < currentSpaceship.getFlightDistance()) {
                    continue;
                }
                closestSpaceship = currentSpaceship;
            }
        }

        try {
            spaceshipService.assignSpaceshipOnMission(closestSpaceship);
        } catch (AssignedOnMissionException e) {
            LOGGER.error(e.getMessage());
        }

        object.setAssignedSpaceship(closestSpaceship);
        return object;
    }
}

