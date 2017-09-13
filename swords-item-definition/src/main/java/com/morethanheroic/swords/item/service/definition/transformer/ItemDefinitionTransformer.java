package com.morethanheroic.swords.item.service.definition.transformer;

import com.morethanheroic.swords.definition.transformer.DefinitionTransformer;
import com.morethanheroic.swords.effect.service.transformer.EffectDefinitionListTransformer;
import com.morethanheroic.swords.item.domain.ItemDefinition;
import com.morethanheroic.swords.item.service.definition.loader.domain.RawItemDefinition;
import com.morethanheroic.swords.item.service.definition.transformer.modifier.ItemModifierDefinitionListTransformer;
import com.morethanheroic.swords.item.service.definition.transformer.price.ItemPriceDefinitionListTransformer;
import com.morethanheroic.swords.item.service.definition.transformer.requirement.ItemRequirementDefinitionListTransformer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Transform a {@link RawItemDefinition} to {@link ItemDefinition}.
 */
@Service
@RequiredArgsConstructor
public class ItemDefinitionTransformer implements DefinitionTransformer<ItemDefinition, RawItemDefinition> {

    private final ItemModifierDefinitionListTransformer itemModifierDefinitionListTransformer;
    private final ItemRequirementDefinitionListTransformer itemRequirementDefinitionListTransformer;
    private final ItemPriceDefinitionListTransformer itemPriceDefinitionListTransformer;
    private final EffectDefinitionListTransformer effectDefinitionListTransformer;

    @Override
    @SuppressWarnings("unchecked")
    public ItemDefinition transform(final RawItemDefinition rawItemDefinition) {
        return ItemDefinition.builder()
                .id(rawItemDefinition.getId())
                .name(rawItemDefinition.getName())
                .type(rawItemDefinition.getType())
                .rarity(rawItemDefinition.getRarity())
                .subtype(rawItemDefinition.getSubtype())
                .usable(rawItemDefinition.isUsable())
                .weight(rawItemDefinition.getWeight())
                .equipment(rawItemDefinition.isEquipment())
                .description(rawItemDefinition.getDescription())
                .flavour(rawItemDefinition.getFlavour())
                .tradeable(rawItemDefinition.isTradeable())
                .priceDefinitions(itemPriceDefinitionListTransformer.transform(rawItemDefinition.getPriceList()))
                .combatEffects(effectDefinitionListTransformer.transform((List) rawItemDefinition.getEffectList()))
                .modifiers(itemModifierDefinitionListTransformer.transform(rawItemDefinition.getModifiers()))
                .requirements(itemRequirementDefinitionListTransformer.transform(rawItemDefinition.getRequirements()))
                .build();
    }
}
