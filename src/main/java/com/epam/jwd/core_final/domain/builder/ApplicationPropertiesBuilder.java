package com.epam.jwd.core_final.domain.builder;

import com.epam.jwd.core_final.domain.ApplicationProperties;

public class ApplicationPropertiesBuilder {

    private String inputRootDir;
    private String outputRootDir;
    private String crewFileName;
    private String missionsFileName;
    private String spaceshipsFileName;
    private String spacemapFileName;
    private Integer fileRefreshRate;
    private String dateTimeFormat;

    public void setSpacemapFileName(String spacemapFileName) {
        this.spacemapFileName = spacemapFileName;
    }

    public void setInputRootDir(String inputRootDir) {
        this.inputRootDir = inputRootDir;
    }

    public void setOutputRootDir(String outputRootDir) {
        this.outputRootDir = outputRootDir;
    }

    public void setCrewFileName(String crewFileName) {
        this.crewFileName = crewFileName;
    }

    public void setMissionsFileName(String missionsFileName) {
        this.missionsFileName = missionsFileName;
    }

    public void setSpaceshipsFileName(String spaceshipsFileName) {
        this.spaceshipsFileName = spaceshipsFileName;
    }

    public void setFileRefreshRate(Integer fileRefreshRate) {
        this.fileRefreshRate = fileRefreshRate;
    }

    public void setDateTimeFormat(String dateTimeFormat) {
        this.dateTimeFormat = dateTimeFormat;
    }

    public ApplicationProperties buildApplicationProperties() {
        return new ApplicationProperties(inputRootDir, outputRootDir,
                crewFileName, spacemapFileName,
                missionsFileName, spaceshipsFileName,
                fileRefreshRate, dateTimeFormat);
    }

}
