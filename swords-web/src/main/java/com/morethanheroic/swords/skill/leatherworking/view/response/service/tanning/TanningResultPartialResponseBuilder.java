package com.morethanheroic.swords.skill.leatherworking.view.response.service.tanning;

import com.morethanheroic.response.domain.PartialResponse;
import com.morethanheroic.response.service.PartialResponseBuilder;
import com.morethanheroic.swords.skill.leatherworking.view.response.domain.configuration.tanning.TanningResultPartialResponse;
import com.morethanheroic.swords.skill.leatherworking.view.response.domain.configuration.tanning.TanningStartResponseBuilderConfiguration;
import org.springframework.stereotype.Service;

@Service
public class TanningResultPartialResponseBuilder implements PartialResponseBuilder<TanningStartResponseBuilderConfiguration> {

    @Override
    public PartialResponse build(TanningStartResponseBuilderConfiguration tanningStartResponseBuilderConfiguration) {
        return TanningResultPartialResponse.builder()
                .result(tanningStartResponseBuilderConfiguration.getTanningResult())
                .build();
    }
}
