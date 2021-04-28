package com.epam.jwd.core_final.strategy;

import com.epam.jwd.core_final.domain.Point;
import com.epam.jwd.core_final.strategy.impl.PythagoreanTheoremStrategy;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PythagoreanTheoremTest {

    private PythagoreanTheoremStrategy strategy;

    @Before
    public void setUp() throws Exception {
        strategy = PythagoreanTheoremStrategy.getInstance();
    }

    private Point firstPoint;
    private Point secondPoint;

    @Test
    public void calculateDistanceBetweenPoints_correctDistance_always() {
        firstInit();
        assertEquals((Double) 1.0, (Double) strategy.calculateDistance(firstPoint, secondPoint));
        secondInit();
        assertEquals((Double) 1.0, (Double) strategy.calculateDistance(firstPoint, secondPoint));
        thirdInit();
        assertEquals((Double) 5.0, (Double) strategy.calculateDistance(firstPoint, secondPoint));
    }

    private void firstInit() {
        secondPoint = new Point(1, 3);
        firstPoint = new Point(1, 2);
    }

    private void secondInit() {
        secondPoint = new Point(1, 2);
        firstPoint = new Point(2, 2);
    }

    private void thirdInit() {
        secondPoint = new Point(4, 1);
        firstPoint = new Point(1, 5);
    }

}