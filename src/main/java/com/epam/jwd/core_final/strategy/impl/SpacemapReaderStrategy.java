package com.epam.jwd.core_final.strategy.impl;

import com.epam.jwd.core_final.domain.Planet;
import com.epam.jwd.core_final.domain.Point;
import com.epam.jwd.core_final.domain.Spacemap;
import com.epam.jwd.core_final.factory.impl.SpacemapFactory;
import com.epam.jwd.core_final.service.impl.SimpleSpacemapService;
import com.epam.jwd.core_final.strategy.api.ReadFileStrategy;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class SpacemapReaderStrategy implements ReadFileStrategy {

    private final SimpleSpacemapService SERVICE = SimpleSpacemapService.getInstance();
    private final SpacemapFactory FACTORY = SpacemapFactory.getInstance();
    private static SpacemapReaderStrategy instance;

    private SpacemapReaderStrategy() {
    }

    public static SpacemapReaderStrategy getInstance() {
        if (instance == null) {
            instance = new SpacemapReaderStrategy();
        }
        return instance;
    }

    @Override
    public void readFile(String path, String filename) throws FileNotFoundException {
        String filePath = "src" + File.separator +
                "main" + File.separator + "resources" +
                File.separator + path + File.separator + filename;
        Scanner scanner = new Scanner(new File(filePath));
        readSpacemap(scanner);
    }

    private void readSpacemap(Scanner scanner) {
        ArrayList<ArrayList<String>> planetsMapNames = new ArrayList<>();
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (line.charAt(0) != '#') {
                planetsMapNames.add(parseLine(line));
            }
        }
        ArrayList<ArrayList<Planet>> planets = parseString(planetsMapNames);

        Spacemap newMap = FACTORY.createSpacemap(planets);
        SERVICE.createSpacemap(newMap);
    }

    private ArrayList<ArrayList<Planet>> parseString(ArrayList<ArrayList<String>> planetsStrings) {
        ArrayList<ArrayList<Planet>> planets = new ArrayList<>();
        for (int i = 0; i < planetsStrings.size(); i++) {
            ArrayList<Planet> line = new ArrayList<>();
            for (int j = 0; j < planetsStrings.get(i).size(); j++) {
                if (planetsStrings.get(i).get(j).equals("null")) {
                    line.add(null);
                } else {
                    line.add(new Planet(planetsStrings.get(i).get(j), new Point(j, i)));
                }
            }
            planets.add(line);
        }
        return planets;
    }

    private ArrayList<String> parseLine(String line) {
        String[] names = line.split(",");
        return new ArrayList<>(Arrays.asList(names));
    }

}
