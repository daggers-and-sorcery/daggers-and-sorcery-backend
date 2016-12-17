package com.morethanheroic.swords.profile.service.response.special;

import com.morethanheroic.response.domain.PartialResponse;
import com.morethanheroic.response.service.PartialResponseBuilder;
import com.morethanheroic.swords.profile.response.service.special.SpecialPartialResponse;
import com.morethanheroic.swords.profile.response.service.special.SpecialPartialResponseBuilderConfiguration;
import org.springframework.stereotype.Service;

@Service
public class SpecialPartialResponseBuilder implements PartialResponseBuilder<SpecialPartialResponseBuilderConfiguration> {

    @Override
    public PartialResponse build(SpecialPartialResponseBuilderConfiguration specialPartialResponseBuilderConfiguration) {
        return SpecialPartialResponse.builder()
                .isVampire(specialPartialResponseBuilderConfiguration.isVampire())
                .build();
    }
}
