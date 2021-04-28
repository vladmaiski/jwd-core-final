package com.epam.jwd.core_final.context.impl.menu.update;

import com.epam.jwd.core_final.context.ApplicationMenu;
import com.epam.jwd.core_final.criteria.FlightMissionCriteria;
import com.epam.jwd.core_final.domain.FlightMission;
import com.epam.jwd.core_final.domain.MissionResult;
import com.epam.jwd.core_final.service.impl.SimpleMissionService;

import java.util.Optional;

class UpdateFlightMissionMenu {
    private final static SimpleMissionService MISSION_SERVICE = SimpleMissionService.getInstance();

    public static void menu() {
        System.out.println("----------------------------");
        System.out.println("Enter ID of mission you want to update: ");
        int id = ApplicationMenu.receiveUserChoose();

        Optional<FlightMission> optionalFlightMission = MISSION_SERVICE.findMissionByCriteria(
                FlightMissionCriteria.builder().withId(id).build());

        if (!optionalFlightMission.isPresent()) {
            System.out.println("Mission with id = " + id + " - not found, please check your input and try again");
            menu();
        } else {
            updateMissionMenu(optionalFlightMission.get());
        }
        UpdateEntitiesMenu.menu();
    }

    private static void updateMissionMenu(FlightMission mission) {
        System.out.println("Choose property to update: \n" +
                "1) Distance\n" +
                "2) Make Mission Completed");
        int choose = ApplicationMenu.receiveUserChoose();
        switch (choose) {
            case 1:
                updateMissionDistance(mission);
                break;
            case 2:
                updateMissionResult(mission);
                break;
            default:
                System.out.println("It seems you made a mistake. Check given menu and enter value:");
                updateMissionMenu(mission);
                break;
        }
    }

    private static void updateMissionDistance(FlightMission mission) {
        System.out.println("Enter new distance: ");
        int flightDistance = ApplicationMenu.receiveUserChoose();
        mission.setDistance((long) flightDistance);
        MISSION_SERVICE.updateSpaceshipDetails(mission);
    }

    private static void updateMissionResult(FlightMission mission) {
        if (mission.getMissionResult() == MissionResult.CANCELLED) {
            System.out.println("Mission canceled");
            return;
        }
        if (mission.getMissionResult() == MissionResult.COMPLETED) {
            System.out.println("Mission already completed");
            return;
        }
        MISSION_SERVICE.completeMission(mission);
        System.out.println("OK!");
    }

}
