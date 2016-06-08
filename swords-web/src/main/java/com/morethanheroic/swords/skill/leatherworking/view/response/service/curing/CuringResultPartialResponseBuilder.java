package com.morethanheroic.swords.skill.leatherworking.view.response.service.curing;

import com.morethanheroic.response.domain.PartialResponse;
import com.morethanheroic.response.service.PartialResponseBuilder;
import com.morethanheroic.swords.skill.leatherworking.view.response.domain.configuration.curing.CuringResultPartialResponse;
import com.morethanheroic.swords.skill.leatherworking.view.response.domain.configuration.curing.CuringStartResponseBuilderConfiguration;
import org.springframework.stereotype.Service;

@Service
public class CuringResultPartialResponseBuilder implements PartialResponseBuilder<CuringStartResponseBuilderConfiguration> {

    @Override
    public PartialResponse build(CuringStartResponseBuilderConfiguration curingStartResponseBuilderConfiguration) {
        return CuringResultPartialResponse.builder()
                .result(curingStartResponseBuilderConfiguration.getCuringResult())
                .build();
    }
}
