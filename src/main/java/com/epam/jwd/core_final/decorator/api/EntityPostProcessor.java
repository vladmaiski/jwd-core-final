package com.epam.jwd.core_final.decorator.api;

import com.epam.jwd.core_final.domain.BaseEntity;
import com.epam.jwd.core_final.exception.AssignOnMissionException;

public interface EntityPostProcessor<T extends BaseEntity> {

    T process(T object) throws AssignOnMissionException;

}
