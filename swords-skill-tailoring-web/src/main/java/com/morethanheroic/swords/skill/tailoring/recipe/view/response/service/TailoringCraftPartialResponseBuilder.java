package com.morethanheroic.swords.skill.tailoring.recipe.view.response.service;

import com.morethanheroic.response.domain.PartialResponse;
import com.morethanheroic.response.service.PartialResponseBuilder;
import com.morethanheroic.swords.skill.tailoring.recipe.view.response.service.domain.TailoringCraftPartialResponse;
import com.morethanheroic.swords.skill.tailoring.recipe.view.response.service.domain.TailoringCraftResponseBuilderConfiguration;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TailoringCraftPartialResponseBuilder implements PartialResponseBuilder<TailoringCraftResponseBuilderConfiguration> {

    @Override
    public PartialResponse build(TailoringCraftResponseBuilderConfiguration tailoringCraftResponseBuilderConfiguration) {
        return TailoringCraftPartialResponse.builder()
                .tailoringResult(tailoringCraftResponseBuilderConfiguration.getTailoringResult())
                .build();
    }
}
