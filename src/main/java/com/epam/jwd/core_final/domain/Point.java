package com.epam.jwd.core_final.domain;

public class Point {

    double xValue;
    double yValue;

    public Point(double xValue, double yValue) {
        this.xValue = xValue;
        this.yValue = yValue;
    }

    public double getxValue() {
        return xValue;
    }

    public double getyValue() {
        return yValue;
    }

    @Override
    public String toString() {
        return "Point{" +
                "xValue=" + xValue +
                ", yValue=" + yValue +
                '}';
    }

}
