package com.morethanheroic.swords.skill.imbuing.view.response.service;

import com.morethanheroic.localization.MessageResolver;
import com.morethanheroic.response.domain.PartialResponse;
import com.morethanheroic.response.service.PartialResponseBuilder;
import com.morethanheroic.swords.skill.imbuing.service.domain.ImbuingResult;
import com.morethanheroic.swords.skill.imbuing.view.response.service.domain.ImbuingCraftResponseBuilderConfiguration;
import com.morethanheroic.swords.skill.imbuing.view.response.service.domain.ImbuingRecipeCraftResultPartialResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ImbuingRecipeCraftResultPartialResponseBuilder implements PartialResponseBuilder<ImbuingCraftResponseBuilderConfiguration> {

    private final MessageResolver messageResolver;

    @Override
    public PartialResponse build(ImbuingCraftResponseBuilderConfiguration imbuingCraftResponseBuilderConfiguration) {
        return ImbuingRecipeCraftResultPartialResponse.builder()
                .successful(imbuingCraftResponseBuilderConfiguration.getImbuingResult() == ImbuingResult.SUCCESSFUL)
                .result(resolveResultMessage(imbuingCraftResponseBuilderConfiguration.getImbuingResult()))
                .build();
    }

    private String resolveResultMessage(final ImbuingResult craftingResult) {
        return messageResolver.resolveMessage("IMBUE_RECIPE_CRAFT_" + craftingResult.name());
    }
}
