package com.epam.jwd.core_final.domain;

/**
 * Expected fields:
 * <p>
 * location could be a simple class Point with 2 coordinates
 */
public class Planet extends AbstractBaseEntity{

    private static Long id = 0L;
    private Point location;

    //TODO Point


    public Planet(String name, Point location) {
        super(id++, name);
        this.location = location;
    }

    public Point getLocation() {
        return location;
    }

    public void setLocation(Point location) {
        this.location = location;
    }

}
