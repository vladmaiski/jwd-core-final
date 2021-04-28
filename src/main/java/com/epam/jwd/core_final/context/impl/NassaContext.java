package com.epam.jwd.core_final.context.impl;

import com.epam.jwd.core_final.context.ApplicationContext;
import com.epam.jwd.core_final.domain.*;
import com.epam.jwd.core_final.exception.InvalidStateException;
import com.epam.jwd.core_final.exception.UnknownEntityException;
import com.epam.jwd.core_final.strategy.impl.CrewMembersReaderStrategy;
import com.epam.jwd.core_final.strategy.impl.MissionsReaderStrategy;
import com.epam.jwd.core_final.strategy.impl.SpacemapReaderStrategy;
import com.epam.jwd.core_final.strategy.impl.SpaceshipReaderStrategy;
import com.epam.jwd.core_final.util.PropertyReaderUtil;
import com.epam.jwd.core_final.util.Reader;

import java.io.FileNotFoundException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;

// todo
public class NassaContext implements ApplicationContext {

    // no getters/setters for them
    private final Collection<CrewMember> crewMembers = new ArrayList<>();
    private final Collection<Spaceship> spaceships = new ArrayList<>();
    private final Collection<FlightMission> missions = new ArrayList<>();
    private final Spacemap planetMap = new Spacemap(new ArrayList<>());

    private final Reader READER = Reader.getInstance();
    private final ApplicationProperties APP_PROPERTIES = (ApplicationProperties) PropertyReaderUtil.getProperties().get("applicationProperties");

    private static NassaContext instance;

    private NassaContext() {
    }

    public static NassaContext getInstance() {
        if (instance == null) {
            instance = new NassaContext();
        }
        return instance;
    }

    public DateTimeFormatter receiveDateTimeFormatter() {
        return DateTimeFormatter.ofPattern(APP_PROPERTIES.getDateTimeFormat());
    }


    @Override
    @SuppressWarnings("unchecked")
    public <T extends BaseEntity> Collection<T> retrieveBaseEntityList(Class<T> tClass) {
        if (tClass == null) {
            throw new IllegalArgumentException("tClass can't be null");
        }

        switch (tClass.getSimpleName()) {
            case "CrewMember":
                return (Collection<T>) crewMembers;
            case "Planet":
                return (Collection<T>) planetMap.getPlanets();
            case "Spaceship":
                return (Collection<T>) spaceships;
            case "FlightMission":
                return (Collection<T>) missions;
            default:
                throw new UnknownEntityException(tClass.getSimpleName());
        }
    }

    /**
     * You have to read input files, populate collections
     *
     * @throws InvalidStateException
     */
    @Override
    public void init() throws InvalidStateException {
        initCrewMembers();
        initSpaceships();
        initPlanetMap();
        initMissions();
    }

    private void initCrewMembers() throws InvalidStateException {
        READER.setReadFileStrategy(CrewMembersReaderStrategy.getInstance());
        try {
            READER.readFile(APP_PROPERTIES.getInputRootDir(), APP_PROPERTIES.getCrewFileName());
        } catch (FileNotFoundException e) {
            throw new InvalidStateException(e.getMessage());
        }
    }

    private void initSpaceships() throws InvalidStateException {
        READER.setReadFileStrategy(SpaceshipReaderStrategy.getInstance());
        try {
            READER.readFile(APP_PROPERTIES.getInputRootDir(), APP_PROPERTIES.getSpaceshipsFileName());
        } catch (FileNotFoundException e) {
            throw new InvalidStateException(e.getMessage());
        }
    }

    private void initPlanetMap() throws InvalidStateException {
        READER.setReadFileStrategy(SpacemapReaderStrategy.getInstance());
        try {
            READER.readFile(APP_PROPERTIES.getInputRootDir(), APP_PROPERTIES.getSpacemapFileName());
        } catch (FileNotFoundException e) {
            throw new InvalidStateException(e.getMessage());
        }
    }

    private void initMissions() throws InvalidStateException {
        READER.setReadFileStrategy(MissionsReaderStrategy.getInstance());
        try {
            READER.readFile(APP_PROPERTIES.getInputRootDir(), APP_PROPERTIES.getMissionsFileName());
        } catch (FileNotFoundException e) {
            throw new InvalidStateException(e.getMessage());
        }
    }

}
