package com.epam.jwd.core_final.decorator.impl;

import com.epam.jwd.core_final.decorator.api.EntityAbstractDecorator;
import com.epam.jwd.core_final.decorator.api.EntityPostProcessor;
import com.epam.jwd.core_final.domain.FlightMission;
import com.epam.jwd.core_final.factory.EntityFactory;

import java.util.Arrays;
import java.util.List;

public class PostProcessingFlightMissionFactoryDecorator extends EntityAbstractDecorator<FlightMission> {

    List<EntityPostProcessor<FlightMission>> postProcessors = Arrays.asList(SpaceshipAutoCompletePostProcessing.getInstance(),
                                                                CrewAutoCompletePostProcessing.getInstance());

    public PostProcessingFlightMissionFactoryDecorator(EntityFactory<FlightMission> factory) {
        super(factory);
    }

    @Override
    public FlightMission create(Object... args) {
        FlightMission flightMission = factory.create(args);
        for (EntityPostProcessor<FlightMission> postProcessor : postProcessors) {
            flightMission = postProcessor.process(flightMission);
        }
        return flightMission;
    }

}
