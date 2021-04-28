package com.epam.jwd.core_final.domain;

import com.epam.jwd.core_final.exception.UnknownEntityException;
import org.junit.Test;

public class RoleTest {

    @Test(expected = UnknownEntityException.class)
    public void assignCrewOnMission_throwException_whenCrewMemberIsNull() {
        Role.resolveRoleById(9999999L);
    }

}
