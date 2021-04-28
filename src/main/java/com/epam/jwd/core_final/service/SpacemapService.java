package com.epam.jwd.core_final.service;

import com.epam.jwd.core_final.domain.Planet;
import com.epam.jwd.core_final.domain.Spacemap;

import java.util.List;

public interface SpacemapService {

    Planet getRandomPlanet();

    List<Planet> findAllPlanets();

    void createSpacemap(Spacemap spacemap) throws RuntimeException;

    // Dijkstra ?
    int getDistanceBetweenPlanets(Planet first, Planet second);
}
