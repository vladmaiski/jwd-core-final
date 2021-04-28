package com.epam.jwd.core_final.factory;

import com.epam.jwd.core_final.domain.FlightMission;
import com.epam.jwd.core_final.factory.impl.FlightMissionFactory;
import org.junit.BeforeClass;
import org.junit.Test;

import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;

public class FlightMissionFactoryTest {

    private static FlightMissionFactory missionFactory;

    @BeforeClass
    public static void setUp() {
        missionFactory = FlightMissionFactory.getInstance();
    }

    @Test
    public void create_correctObject_always() {
        String name = "mission";
        Long flightDistance = 150000L;
        LocalDateTime startDate = LocalDateTime.now();
        LocalDateTime endDate = LocalDateTime.now();

        FlightMission mission = missionFactory.create(name, startDate, endDate, flightDistance);

        assertEquals(name, mission.getName());
        assertEquals(flightDistance, mission.getDistance());
        assertEquals(startDate, mission.getStartDate());
        assertEquals(endDate, mission.getEndDate());
    }
}