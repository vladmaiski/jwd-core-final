package com.epam.jwd.core_final.strategy.impl;

import com.epam.jwd.core_final.domain.CrewMember;
import com.epam.jwd.core_final.domain.Rank;
import com.epam.jwd.core_final.domain.Role;
import com.epam.jwd.core_final.exception.DuplicateEntityException;
import com.epam.jwd.core_final.exception.UnknownEntityException;
import com.epam.jwd.core_final.factory.impl.CrewMemberFactory;
import com.epam.jwd.core_final.service.impl.SimpleCrewService;
import com.epam.jwd.core_final.strategy.api.ReadFileStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class CrewMembersReaderStrategy implements ReadFileStrategy {

    private final CrewMemberFactory CREW_MEMBER_FACTORY = CrewMemberFactory.getInstance();
    private final SimpleCrewService CREW_MEMBER_SERVICE = SimpleCrewService.getInstance();
    private final Logger LOGGER = LoggerFactory.getLogger(CrewMembersReaderStrategy.class);
    private static CrewMembersReaderStrategy instance;

    private CrewMembersReaderStrategy() {
    }

    public static CrewMembersReaderStrategy getInstance() {
        if (instance == null) {
            instance = new CrewMembersReaderStrategy();
        }
        return instance;
    }

    @Override
    public void readFile(String rootDir, String fileName) throws FileNotFoundException {
        String path = "src" + File.separator + "main" + File.separator
                + "resources" + File.separator + rootDir + File.separator + fileName;
        Scanner scanner = new Scanner(new File(path));
        parseFile(scanner);

    }


    private void parseFile(Scanner scanner) {
        while (scanner.hasNextLine()) {
            String nextLine = scanner.nextLine();
            if (nextLine.charAt(0) != '#') {
                String[] crewMembersData = nextLine.split("[;]");
                for (String crewData : crewMembersData) {
                    createCrewMember(crewData.split("[,]"));
                }
            }
        }
        scanner.close();
    }

    private void createCrewMember(String[] args) {
        try {
            CrewMember crewMember = CREW_MEMBER_FACTORY.create(
                    args[1],
                    Role.resolveRoleById(Long.parseLong(args[0])),
                    Rank.resolveRankById(Long.parseLong(args[2])));

            CREW_MEMBER_SERVICE.createCrewMember(crewMember);
        } catch (DuplicateEntityException | UnknownEntityException e) {
            LOGGER.error(e.getMessage());
        }
    }
}
