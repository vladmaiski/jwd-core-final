package com.epam.jwd.core_final.decorator.api;

import com.epam.jwd.core_final.domain.BaseEntity;

public interface EntityPostProcessor<T extends BaseEntity> {

    T process(T object);

}
