package com.epam.jwd.core_final.service;

import com.epam.jwd.core_final.domain.CrewMember;
import com.epam.jwd.core_final.domain.Rank;
import com.epam.jwd.core_final.domain.Role;
import com.epam.jwd.core_final.exception.AssignOnMissionException;
import com.epam.jwd.core_final.service.impl.SimpleCrewService;
import org.junit.Before;
import org.junit.Test;

public class SimpleCrewMemberServiceTest {

    private SimpleCrewService service;

    @Before
    public void setUp() throws Exception {
        service = SimpleCrewService.getInstance();
    }

    @Test(expected = IllegalArgumentException.class)
    public void assignCrewOnMission_throwExeption_whenCrewMemberIsNull() {
        service.assignCrewMemberOnMission(null);
    }


    @Test(expected = AssignOnMissionException.class)
    public void assignCrewOnMission_throwException_whenIsAlreadyAssigned() {
        CrewMember member = new CrewMember("someName", Role.resolveRoleById(1L), Rank.resolveRankById(1L));
        member.setReadyForNextMissions(false);
        service.assignCrewMemberOnMission(member);
    }

}
