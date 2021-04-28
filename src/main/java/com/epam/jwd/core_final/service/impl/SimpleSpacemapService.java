package com.epam.jwd.core_final.service.impl;

import com.epam.jwd.core_final.context.impl.NassaContext;
import com.epam.jwd.core_final.domain.Planet;
import com.epam.jwd.core_final.domain.Spacemap;
import com.epam.jwd.core_final.exception.DuplicateEntityException;
import com.epam.jwd.core_final.service.SpacemapService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    public Planet getById(int id) {
        List<Planet> planets = findAllPlanets();
        for (Planet planet : planets) {
            if (planet.getId() == id)
                return planet;
        }
        return null;
    }

    @Override
    public void createSpacemap(Spacemap spacemap) throws DuplicateEntityException {
        for (Planet newPlanet : spacemap.getPlanets()) {
            Optional<Planet> optionalSpaceship = findAllPlanets().stream()
                    .filter(e -> newPlanet.getName().equals(e.getName()))
                    .findFirst();

            if (optionalSpaceship.isPresent()) {
                throw new DuplicateEntityException("This entity" + newPlanet.getName() + "is already consist");
            } else {
                findAllPlanets().add(newPlanet);
            }
        }
    }

    @Override
    public Planet getRandomPlanet() {
        return null;
    }

    @Override
    public List<Planet> findAllPlanets() {
        return (List<Planet>) NASSA_CONTEXT.retrieveBaseEntityList(Planet.class);
    }

    @Override
    public int getDistanceBetweenPlanets(Planet first, Planet second) {
        return 0;
    }

}
