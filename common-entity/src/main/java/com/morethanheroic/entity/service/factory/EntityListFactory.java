package com.morethanheroic.entity.service.factory;

import com.morethanheroic.entity.domain.Entity;

import java.util.List;

public interface EntityListFactory<RESULT extends Entity, IDENTIFIER> {

    List<RESULT> getEntity(final IDENTIFIER id);
}
