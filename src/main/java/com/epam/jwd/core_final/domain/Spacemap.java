package com.epam.jwd.core_final.domain;

import java.util.ArrayList;

public class Spacemap {

    private ArrayList<ArrayList<Planet>> planetsMap = new ArrayList<>();
    private ArrayList<Planet> planets = new ArrayList<>();

    public Spacemap(ArrayList<ArrayList<Planet>> planetsMap) {
        this.planetsMap = planetsMap;
        planets = onlyPlanets();

    }

    public ArrayList<ArrayList<Planet>> getMap() {
        return planetsMap;
    }

    public ArrayList<Planet> getPlanets() {
        return planets;
    }

    private ArrayList<Planet> onlyPlanets() {
        ArrayList<Planet> realPlanets = new ArrayList<>();
        for (ArrayList<Planet> line : planetsMap) {
            for (Planet planet : line) {
                if (planet != null) {
                    realPlanets.add(planet);
                }
            }
        }
        return realPlanets;
    }

}
