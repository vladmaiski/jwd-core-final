package com.epam.jwd.core_final.service.impl;

import com.epam.jwd.core_final.context.impl.NassaContext;
import com.epam.jwd.core_final.criteria.Criteria;
import com.epam.jwd.core_final.criteria.FlightMissionCriteria;
import com.epam.jwd.core_final.domain.FlightMission;
import com.epam.jwd.core_final.domain.MissionResult;
import com.epam.jwd.core_final.exception.DuplicateEntityException;
import com.epam.jwd.core_final.service.MissionService;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

public class SimpleMissionService implements MissionService {
    private static final NassaContext NASSA_CONTEXT = NassaContext.getInstance();
    private static SimpleMissionService instance;

    private SimpleMissionService() {
    }

    public static SimpleMissionService getInstance() {
        if (instance == null) {
            instance = new SimpleMissionService();
        }
        return instance;
    }

    @Override
    public List<FlightMission> findAllMissions() {
        return (List<FlightMission>) NASSA_CONTEXT.retrieveBaseEntityList(FlightMission.class);
    }

    @Override
    public List<FlightMission> findAllMissionsByCriteria(Criteria<? extends FlightMission> criteria) {
        return findAllMissions().stream()
                .filter(e -> isMissionSuitable(criteria, e))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<FlightMission> findMissionByCriteria(Criteria<? extends FlightMission> criteria) {
        return findAllMissionsByCriteria(criteria).stream().findFirst();
    }

    @Override
    public FlightMission updateSpaceshipDetails(FlightMission flightMission) {
        Optional<FlightMission> optionalMission = findAllMissions().stream()
                .filter(e -> flightMission.getName().equals(e.getName()))
                .findFirst();

        if (optionalMission.isPresent()) {
            optionalMission.get().setAssignedSpaceship(flightMission.getAssignedSpaceship());
            optionalMission.get().setDistance(flightMission.getDistance());
            optionalMission.get().setAssignedCrew(flightMission.getAssignedCrew());
            optionalMission.get().setStartDate(flightMission.getStartDate());
            optionalMission.get().setEndDate(flightMission.getEndDate());
            optionalMission.get().setMissionResult(flightMission.getMissionResult());
        } else {
            throw new IllegalArgumentException(flightMission.getName() + " - this mission not found");
        }
        return optionalMission.get();
    }

    @Override
    public FlightMission createMission(FlightMission flightMission) throws DuplicateEntityException {
        Optional<FlightMission> optionalFlightMission = findAllMissions().stream()
                .filter(e -> flightMission.getName().equals(e.getName())
                        && flightMission.getStartDate().equals(e.getStartDate()))
                .findFirst();

        if (optionalFlightMission.isPresent()) {
            throw new DuplicateEntityException("This mission" + flightMission.getName() + ", "
                    + flightMission.getStartDate().toString() + "is already consist");
        } else {
            findAllMissions().add(flightMission);
            return flightMission;
        }
    }

    private boolean isMissionSuitable(Criteria<? extends FlightMission> criteria, FlightMission mission) {
        FlightMissionCriteria missionCriteria = (FlightMissionCriteria) criteria;
        boolean isMissionSuitable = false;

        if (missionCriteria.getName() != null) {
            isMissionSuitable = missionCriteria.getName().equals(mission.getName());
        }
        if (missionCriteria.getId() != null) {
            isMissionSuitable = missionCriteria.getId().equals(mission.getId());
        }
        if (missionCriteria.getMissionResult() != null) {
            isMissionSuitable = missionCriteria.getMissionResult().equals(mission.getMissionResult());
        }
        if (missionCriteria.getAssignedSpaceship() != null) {
            isMissionSuitable = missionCriteria.getAssignedSpaceship().equals(mission.getAssignedSpaceship());
        }
        if (missionCriteria.getCrewMember() != null) {
            isMissionSuitable = mission.getAssignedCrew().contains(missionCriteria.getCrewMember());
        }
        if (missionCriteria.getStartDate() != null) {
            isMissionSuitable = mission.getStartDate().equals(missionCriteria.getStartDate());
        }
        if (missionCriteria.getEndDate() != null) {
            isMissionSuitable = mission.getEndDate().equals(missionCriteria.getEndDate());
        }

        return isMissionSuitable;
    }

    public void completeMission(FlightMission flightMission) {
        flightMission.setMissionResult(MissionResult.COMPLETED);
        flightMission.getAssignedSpaceship().setReadyForNextMissions(true);
        flightMission.getAssignedCrew().forEach(crewMember -> crewMember.setReadyForNextMissions(true));
    }

    public void receiveRandomResultOfMissions() {
        Collection<FlightMission> listOfMissions = NASSA_CONTEXT.retrieveBaseEntityList(FlightMission.class);
        for (FlightMission flightMission : listOfMissions) {
            if (flightMission.getMissionResult() != MissionResult.COMPLETED) {
                receiveStatusOfMission(flightMission);
            }
        }
    }

    private void receiveStatusOfMission(FlightMission flightMission) {
        Random random = new Random();
        int randomValue = random.nextInt(100);
        if (randomValue <= 20) {
            flightMission.setMissionResult(MissionResult.FAILED);
        } else if (randomValue <= 60){
            completeMission(flightMission);
        }
    }

}
