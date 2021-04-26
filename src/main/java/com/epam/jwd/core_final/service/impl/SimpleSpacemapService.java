package com.epam.jwd.core_final.service.impl;

import com.epam.jwd.core_final.context.impl.NassaContext;
import com.epam.jwd.core_final.domain.Planet;
import com.epam.jwd.core_final.service.SpacemapService;

import java.util.ArrayList;

public class SimpleSpacemapService implements SpacemapService {

    private final NassaContext NASSA_CONTEXT = NassaContext.getInstance();
    private static SimpleSpacemapService instance;
    private ArrayList<ArrayList<Planet>> planetsMap;

    private SimpleSpacemapService() {
    }

    public static SimpleSpacemapService getInstance() {
        if (instance == null) {
            instance = new SimpleSpacemapService();
        }
        return instance;
    }

    public void createSpacemap() {

    }

    @Override
    public Planet getRandomPlanet() {
        return null;
    }

    @Override
    public int getDistanceBetweenPlanets(Planet first, Planet second) {
        return 0;
    }

}
