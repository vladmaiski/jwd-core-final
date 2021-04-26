package com.epam.jwd.core_final.util;

import com.epam.jwd.core_final.strategy.api.ReadFileStrategy;

import java.io.FileNotFoundException;

public class Reader {

    private static Reader instance;

    private Reader() {
    }

    ReadFileStrategy readFileStrategy;

    public static Reader getInstance() {
        if (instance == null) {
            instance = new Reader();
        }
        return instance;
    }

    public void setReadFileStrategy(ReadFileStrategy readFileStrategy) {
        this.readFileStrategy = readFileStrategy;
    }

    public void readFile(String path, String filename) throws FileNotFoundException {
        readFileStrategy.readFile(path, filename);
    }

}
