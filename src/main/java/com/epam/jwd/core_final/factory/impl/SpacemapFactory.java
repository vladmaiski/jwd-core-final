package com.epam.jwd.core_final.factory.impl;

import com.epam.jwd.core_final.domain.Planet;
import com.epam.jwd.core_final.domain.Spacemap;

import java.util.ArrayList;

public class SpacemapFactory {

    private static SpacemapFactory instance;

    private SpacemapFactory() {
    }

    public static SpacemapFactory getInstance() {
        if (instance == null) {
            instance = new SpacemapFactory();
        }
        return instance;
    }

    public Spacemap createSpacemap(ArrayList<ArrayList<Planet>> map) {
        return new Spacemap(map);
    }

}
