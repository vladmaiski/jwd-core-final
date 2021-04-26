package com.epam.jwd.core_final.decorator.impl;

import com.epam.jwd.core_final.criteria.CrewMemberCriteria;
import com.epam.jwd.core_final.decorator.api.EntityPostProcessor;
import com.epam.jwd.core_final.domain.CrewMember;
import com.epam.jwd.core_final.domain.FlightMission;
import com.epam.jwd.core_final.domain.Role;
import com.epam.jwd.core_final.service.impl.SimpleCrewService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CrewAutoCompletePostProcessing implements EntityPostProcessor<FlightMission> {

    private static CrewAutoCompletePostProcessing instance;

    public static CrewAutoCompletePostProcessing getInstance() {
        if (instance == null) {
            instance = new CrewAutoCompletePostProcessing();
        }
        return instance;
    }

    @Override
    public FlightMission process(FlightMission object) {
        List<CrewMember> crewMembers = new ArrayList<>();
        SimpleCrewService service = SimpleCrewService.getInstance();
        Map<Role, Short> crew = object.getAssignedSpaceship().getCrew();
        for (Map.Entry<Role, Short> entry: crew.entrySet()) {
            List<CrewMember> potentialCrewMembers = service.findAllCrewMembersByCriteria(CrewMemberCriteria.builder().
                                            withRole(entry.getKey()).isReadyForNextMission(true).build());
            if (potentialCrewMembers.size() < entry.getValue())
                throw new IllegalArgumentException("Not enough workers " + entry.getKey().getName());
            for (int i = 0; i < entry.getValue(); i++) {
                service.assignCrewMemberOnMission(potentialCrewMembers.get(i));
            }
        }

        object.setAssignedCrew(crewMembers);
        return object;
    }
}
