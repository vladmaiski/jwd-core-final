package com.epam.jwd.core_final.decorator.api;

import com.epam.jwd.core_final.domain.BaseEntity;
import com.epam.jwd.core_final.factory.EntityFactory;

public abstract class EntityAbstractDecorator<T extends BaseEntity> implements EntityFactory<T> {

    protected final EntityFactory<T> factory;

    public EntityAbstractDecorator(EntityFactory<T> factory) {
        this.factory = factory;
    }

}
