package com.morethanheroic.swords.response.service;

import com.morethanheroic.swords.response.domain.PartialResponse;

import java.util.Collection;

public interface PartialResponseCollectionBuilder<T extends ResponseBuilderConfiguration> {

    Collection<? extends PartialResponse> build(T responseBuilderConfiguration);
}
