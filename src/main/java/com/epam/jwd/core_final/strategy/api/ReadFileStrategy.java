package com.epam.jwd.core_final.strategy.api;

import java.io.FileNotFoundException;

public interface ReadFileStrategy {

    void readFile(String path, String filename) throws FileNotFoundException;

}
