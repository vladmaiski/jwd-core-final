package com.epam.jwd.core_final.strategy.api;

import com.epam.jwd.core_final.domain.Point;

public interface DistanceCalculatingStrategy {

    double calculateDistance(Point firstPoint, Point secondPoint);

}
