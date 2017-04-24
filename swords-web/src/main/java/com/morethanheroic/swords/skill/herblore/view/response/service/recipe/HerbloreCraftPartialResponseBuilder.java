package com.morethanheroic.swords.skill.herblore.view.response.service.recipe;

import com.morethanheroic.response.domain.PartialResponse;
import com.morethanheroic.response.service.PartialResponseBuilder;
import com.morethanheroic.swords.skill.herblore.view.response.domain.configuration.recipe.HerbloreCraftPartialResponse;
import com.morethanheroic.swords.skill.herblore.view.response.domain.configuration.recipe.HerbloreCraftResponseBuilderConfiguration;
import org.springframework.stereotype.Service;

@Service
public class HerbloreCraftPartialResponseBuilder implements PartialResponseBuilder<HerbloreCraftResponseBuilderConfiguration> {

    @Override
    public PartialResponse build(final HerbloreCraftResponseBuilderConfiguration herbloreCraftResponseBuilderConfiguration) {
        return HerbloreCraftPartialResponse.builder()
                .result(herbloreCraftResponseBuilderConfiguration.getHerbloreResult())
                .build();
    }
}
