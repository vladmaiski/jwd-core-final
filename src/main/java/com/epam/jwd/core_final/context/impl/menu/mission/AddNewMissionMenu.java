package com.epam.jwd.core_final.context.impl.menu.mission;

import com.epam.jwd.core_final.context.ApplicationMenu;
import com.epam.jwd.core_final.decorator.impl.PostProcessingFlightMissionFactoryDecorator;
import com.epam.jwd.core_final.exception.AssignOnMissionException;
import com.epam.jwd.core_final.factory.impl.FlightMissionFactory;
import com.epam.jwd.core_final.service.impl.SimpleMissionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.util.Random;

public class AddNewMissionMenu {
    private static final SimpleMissionService MISSION_SERVICE = SimpleMissionService.getInstance();
    private static final PostProcessingFlightMissionFactoryDecorator MISSION_FACTORY = new PostProcessingFlightMissionFactoryDecorator(FlightMissionFactory.getInstance());
    private static final Logger LOGGER = LoggerFactory.getLogger(AddNewMissionMenu.class);

    public static void createNewMission() {
        MISSION_SERVICE.receiveRandomResultOfMissions();

        String missionName;
        Long missionDistance;
        LocalDateTime startDate;
        LocalDateTime endDate;

        System.out.println("Enter the name of the mission: ");
        missionName = ApplicationMenu.SCANNER.nextLine();

        missionDistance = selectionDistance();
        startDate = LocalDateTime.now().plusDays(new Random().nextInt(20));
        endDate = startDate.plusDays(missionDistance / 5000);
        try {
            MISSION_SERVICE.createMission(MISSION_FACTORY.create(missionName, startDate, endDate, missionDistance));
        } catch (IllegalArgumentException | AssignOnMissionException e) {
            LOGGER.error(e.getMessage());
            System.out.println(e.getMessage());
        }
    }

    private static Long selectionDistance() {
        System.out.println("Enter the distance of the mission: ");
        return (long) ApplicationMenu.receiveUserChoose();
    }

}
