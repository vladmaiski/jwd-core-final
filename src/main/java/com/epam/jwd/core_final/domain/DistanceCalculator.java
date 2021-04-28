package com.epam.jwd.core_final.domain;

import com.epam.jwd.core_final.strategy.api.DistanceCalculatingStrategy;

public class DistanceCalculator implements DistanceCalculatingStrategy {

    DistanceCalculatingStrategy strategy;

    public DistanceCalculator(DistanceCalculatingStrategy strategy) {
        this.strategy = strategy;
    }

    @Override
    public double calculateDistance(Point firstPoint, Point secondPoint) {
        return strategy.calculateDistance(firstPoint, secondPoint);
    }

}
