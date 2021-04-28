package com.epam.jwd.core_final.context.impl.menu.view;

import com.epam.jwd.core_final.context.ApplicationMenu;
import com.epam.jwd.core_final.context.impl.menu.NassaApplicationMenu;
import com.epam.jwd.core_final.domain.DistanceCalculator;
import com.epam.jwd.core_final.domain.Planet;
import com.epam.jwd.core_final.service.impl.SimpleSpacemapService;
import com.epam.jwd.core_final.strategy.impl.PythagoreanTheoremStrategy;
import com.epam.jwd.core_final.util.ConsoleColors;

import java.util.List;

public class ShowPlanetsMenu {

    private static DistanceCalculator distanceCalculator = new DistanceCalculator(PythagoreanTheoremStrategy.getInstance());
    private static final SimpleSpacemapService SERVICE = SimpleSpacemapService.getInstance();

    public static void menu() {
        System.out.println("----------------------------");
        System.out.println("1) Show all planets");
        System.out.println("2) Show distance between planets");
        System.out.println("3) Back");
        handleUserInput(ApplicationMenu.receiveUserChoose());
    }


    private static void handleUserInput(int choose) {
        switch (choose) {
            case 1:
                showAllPlanets();
                break;
            case 2:
                showDistanceBetweenPlanets();
                break;
            case 3:
                NassaApplicationMenu.getInstance().printAvailableOptions();
                break;
            default:
                System.out.println(ConsoleColors.RED_BOLD + "It seems you made a mistake. " +
                        "Check given menu and enter value: " + ConsoleColors.RESET);
                break;
        }
        menu();
    }

    private static void showDistanceBetweenPlanets() {
        Planet firstPlanet = receivePlanetById("Input id of first planet");
        Planet secondPlanet = receivePlanetById("Input id of second planet");
        double distance = distanceCalculator.calculateDistance(firstPlanet.getLocation(), secondPlanet.getLocation());
        System.out.println(ConsoleColors.GREEN_BOLD + "Distance is " + distance + ConsoleColors.RESET);
    }

    private static Planet receivePlanetById(String msg) {
        boolean incorrect = true;
        Planet planet = null;
        while (incorrect) {
            System.out.println(msg);
            int id = ApplicationMenu.receiveUserChoose();
            planet = SERVICE.getById(id);
            if (planet == null) {
                System.out.println(ConsoleColors.RED_BOLD + "Incorrect id, try again" + ConsoleColors.RESET);
            } else {
                incorrect = false;
            }
        }
        return planet;
    }

    private static void showAllPlanets() {
        List<Planet> crewMemberList = SERVICE.findAllPlanets();
        displayPlanetsList(crewMemberList);
    }

    private static void displayPlanetsList(List<Planet> planets) {
        for (Planet planet : planets) {
            System.out.println("[" + planet.getId() + "] " + planet.getName() + " " + planet.getLocation());
        }
    }

}
