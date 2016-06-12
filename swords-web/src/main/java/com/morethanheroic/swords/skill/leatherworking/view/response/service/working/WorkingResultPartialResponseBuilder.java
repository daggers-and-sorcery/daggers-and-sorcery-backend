package com.morethanheroic.swords.skill.leatherworking.view.response.service.working;

import com.morethanheroic.response.domain.PartialResponse;
import com.morethanheroic.response.service.PartialResponseBuilder;
import com.morethanheroic.swords.skill.leatherworking.view.response.domain.configuration.working.WorkingResultPartialResponse;
import com.morethanheroic.swords.skill.leatherworking.view.response.domain.configuration.working.WorkingStartResponseBuilderConfiguration;
import org.springframework.stereotype.Service;

@Service
public class WorkingResultPartialResponseBuilder implements PartialResponseBuilder<WorkingStartResponseBuilderConfiguration> {

    @Override
    public PartialResponse build(WorkingStartResponseBuilderConfiguration workingStartResponseBuilderConfiguration) {
        return WorkingResultPartialResponse.builder()
                .result(workingStartResponseBuilderConfiguration.getLeatherworkingResult())
                .build();
    }
}
