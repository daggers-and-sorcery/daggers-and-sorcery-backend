package com.morethanheroic.swords.skill.fletching.view.response.service;

import com.morethanheroic.response.domain.PartialResponse;
import com.morethanheroic.response.service.PartialResponseBuilder;
import com.morethanheroic.swords.skill.fletching.view.response.domain.FletchingCreatePartialResponse;
import com.morethanheroic.swords.skill.fletching.view.response.domain.FletchingCreateResponseBuilderConfiguration;
import org.springframework.stereotype.Service;

@Service
public class FletchingCreatePartialResponseBuilder implements PartialResponseBuilder<FletchingCreateResponseBuilderConfiguration> {

    @Override
    public PartialResponse build(FletchingCreateResponseBuilderConfiguration fletchingCreateResponseBuilderConfiguration) {
        return FletchingCreatePartialResponse.builder()
                .result(fletchingCreateResponseBuilderConfiguration.getFletchingResult())
                .build();
    }
}
