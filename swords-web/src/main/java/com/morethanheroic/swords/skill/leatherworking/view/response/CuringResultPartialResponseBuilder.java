package com.morethanheroic.swords.skill.leatherworking.view.response;

import com.morethanheroic.response.domain.PartialResponse;
import com.morethanheroic.response.service.PartialResponseBuilder;
import com.morethanheroic.swords.skill.leatherworking.view.response.domain.configuration.CuringResultPartialResponse;
import com.morethanheroic.swords.skill.leatherworking.view.response.domain.configuration.CuringStartResponseBuilderConfiguration;
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
