package com.epam.jwd.core_final.strategy.impl;

import com.epam.jwd.core_final.domain.Planet;
import com.epam.jwd.core_final.factory.impl.SpacemapFactory;
import com.epam.jwd.core_final.service.impl.SimpleSpacemapService;
import com.epam.jwd.core_final.strategy.api.ReadFileStrategy;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
        List<ArrayList<String>> planetsMapNames = new ArrayList<>();
        while (scanner.hasNextLine()) {
            planetsMapNames.add(parseLine(scanner.nextLine()));
        }
        ArrayList<ArrayList<Planet>> planets = null;
        //FACTORY.createSpacemap(planets);
    }

    private ArrayList<String> parseLine(String line) {
        String[] names = line.split(",");
        return new ArrayList<>(Arrays.asList(names));
    }

}
