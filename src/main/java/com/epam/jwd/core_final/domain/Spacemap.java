package com.epam.jwd.core_final.domain;

import java.util.ArrayList;

public class Spacemap {

    private ArrayList<ArrayList<Planet>> planets = new ArrayList<>();

    public Spacemap(ArrayList<ArrayList<Planet>> planets) {
        this.planets = planets;
    }

    public ArrayList<ArrayList<Planet>> getMap() {
        return planets;
    }

    public ArrayList<Planet> getPlanets() {
        ArrayList<Planet> realPlanets = new ArrayList<>();
        for (ArrayList<Planet> line : planets) {
            for (Planet planet : line) {
                if (planet != null) {
                    realPlanets.add(planet);
                }
            }
        }
        return realPlanets;
    }

}
