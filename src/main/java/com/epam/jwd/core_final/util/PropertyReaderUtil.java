package com.epam.jwd.core_final.util;

import com.epam.jwd.core_final.domain.ApplicationProperties;
import com.epam.jwd.core_final.domain.builder.ApplicationPropertiesBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public final class PropertyReaderUtil {
    private static final Properties PROPERTIES = new Properties();
    private static final Logger LOGGER = LoggerFactory.getLogger(PropertyReaderUtil.class);

    private PropertyReaderUtil() {
    }

    public static Properties getProperties() {
        return PROPERTIES;
    }

    /**
     * try-with-resource using FileInputStream
     *
     * @see {https://www.netjstech.com/2017/09/how-to-read-properties-file-in-java.html for an example}
     * <p>
     * as a result - you should populate {@link ApplicationProperties} with corresponding
     * values from property file
     */
    public static void loadProperties() {
        LOGGER.info("Loading application properties");
        final String propertiesFileName = "src/main/resources/application.properties";
        try (FileInputStream inputStream = new FileInputStream(propertiesFileName)) {
            PROPERTIES.load(inputStream);
            populateApplicationProperties();
        } catch (IOException e) {
            e.printStackTrace();
            //TODO: LOGGER or new Exception(delete)
        }
    }

    private static void populateApplicationProperties() {
        ApplicationPropertiesBuilder builder = new ApplicationPropertiesBuilder();
        builder.setInputRootDir(PROPERTIES.getProperty("inputRootDir"));
        builder.setOutputRootDir(PROPERTIES.getProperty("outputRootDir"));
        builder.setCrewFileName(PROPERTIES.getProperty("crewFileName"));
        builder.setMissionsFileName(PROPERTIES.getProperty("missionsFileName"));
        builder.setSpacemapFileName(PROPERTIES.getProperty("spacemapFileName"));
        builder.setSpaceshipsFileName(PROPERTIES.getProperty("spaceshipsFileName"));
        builder.setFileRefreshRate(Integer.parseInt(PROPERTIES.getProperty("fileRefreshRate")));
        builder.setDateTimeFormat(PROPERTIES.getProperty("dateTimeFormat"));
        ApplicationProperties applicationProperties = builder.buildApplicationProperties();
        PROPERTIES.put("applicationProperties", applicationProperties);
    }

}
