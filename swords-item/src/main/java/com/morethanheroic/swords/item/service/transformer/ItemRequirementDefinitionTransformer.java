package com.morethanheroic.swords.item.service.transformer;

import com.morethanheroic.swords.definition.transformer.DefinitionTransformer;
import com.morethanheroic.swords.item.domain.ItemRequirementDefinition;
import com.morethanheroic.swords.item.service.loader.domain.RawItemRequirementDefinition;
import org.springframework.stereotype.Service;

/**
 * Transform a {@link RawItemRequirementDefinition} to a {@link ItemRequirementDefinition}.
 */
@Service
public class ItemRequirementDefinitionTransformer implements DefinitionTransformer<ItemRequirementDefinition, RawItemRequirementDefinition> {

    public ItemRequirementDefinition transform(RawItemRequirementDefinition rawItemRequirementDefinition) {
        return ItemRequirementDefinition.builder()
                .amount(rawItemRequirementDefinition.getAmount())
                .requirement(rawItemRequirementDefinition.getRequirement())
                .build();
    }
}
