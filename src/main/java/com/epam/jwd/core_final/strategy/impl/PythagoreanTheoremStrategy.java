package com.epam.jwd.core_final.strategy.impl;

import com.epam.jwd.core_final.domain.Point;
import com.epam.jwd.core_final.strategy.api.DistanceCalculatingStrategy;

public class PythagoreanTheoremStrategy implements DistanceCalculatingStrategy {

    private static PythagoreanTheoremStrategy instance;

    private PythagoreanTheoremStrategy() {
    }

    public static PythagoreanTheoremStrategy getInstance() {
        if (instance == null) {
            instance = new PythagoreanTheoremStrategy();
        }
        return instance;
    }

    @Override
    public double calculateDistance(Point firstPoint, Point secondPoint) {
        long deltaX = Math.abs(firstPoint.getXValue() - secondPoint.getXValue());
        long deltaY = Math.abs(firstPoint.getYValue() - secondPoint.getYValue());
        if (deltaX == 0) {
            return deltaY;
        }
        if (deltaY == 0) {
            return deltaX;
        }
        return Math.sqrt(Math.pow(deltaX, 2) + Math.pow(deltaY, 2));
    }

}
