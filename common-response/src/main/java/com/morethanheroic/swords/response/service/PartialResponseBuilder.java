package com.morethanheroic.swords.response.service;

import com.morethanheroic.swords.response.domain.PartialResponse;

public interface PartialResponseBuilder<T extends ResponseBuilderConfiguration> {

    PartialResponse build(T responseBuilderConfiguration);
}
