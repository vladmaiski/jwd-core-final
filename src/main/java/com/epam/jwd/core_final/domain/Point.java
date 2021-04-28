package com.epam.jwd.core_final.domain;

public class Point {

    int xValue;
    int yValue;

    public Point(int xValue, int yValue) {
        this.xValue = xValue;
        this.yValue = yValue;
    }

    public int getXValue() {
        return xValue;
    }

    public int getYValue() {
        return yValue;
    }

    @Override
    public String toString() {
        return "Point (" + xValue +
                ", " + yValue +
                ')';
    }

}
