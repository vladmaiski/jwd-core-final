package com.epam.jwd.core_final.domain;

import java.util.Map;

/**
 * crew {@link java.util.Map<Role, Short>}
 * flightDistance {@link Long} - total available flight distance
 * isReadyForNextMissions {@link Boolean} - true by default. Set to false, after first failed mission
 */
public class Spaceship extends AbstractBaseEntity {
    //todo

    private static Long id = 0L;
    private Map<Role, Short> crew;
    private Long flightDistance;
    private boolean isReadyForNextMissions = true;


    public Spaceship(String name, Long flightDistance, Map<Role, Short> crew) {
        super(id++, name);
        this.flightDistance = flightDistance;
        this.crew = crew;
    }


    public Map<Role, Short> getCrew() {
        return crew;
    }

    public Long getFlightDistance() {
        return flightDistance;
    }

    public boolean isReadyForNextMissions() {
        return isReadyForNextMissions;
    }

    public void setCrew(Map<Role, Short> crew) {
        this.crew = crew;
    }

    public void setFlightDistance(Long flightDistance) {
        this.flightDistance = flightDistance;
    }

    public void setReadyForNextMissions(boolean readyForNextMissions) {
        isReadyForNextMissions = readyForNextMissions;
    }

}
