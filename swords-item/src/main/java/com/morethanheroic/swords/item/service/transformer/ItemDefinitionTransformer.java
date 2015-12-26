package com.morethanheroic.swords.item.service.transformer;

import com.morethanheroic.swords.definition.transformer.DefinitionTransformer;
import com.morethanheroic.swords.effect.service.transformer.EffectDefinitionListTransformer;
import com.morethanheroic.swords.effect.service.transformer.EffectDefinitionTransformer;
import com.morethanheroic.swords.item.domain.ItemDefinition;
import com.morethanheroic.swords.item.service.loader.domain.RawItemDefinition;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Transform a {@link RawItemDefinition} to {@link ItemDefinition}.
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ItemDefinitionTransformer implements DefinitionTransformer<ItemDefinition, RawItemDefinition> {

    @NonNull
    private final EffectDefinitionTransformer combatEffectDefinitionTransformer;

    @NonNull
    private final ItemModifierDefinitionTransformer itemModifierDefinitionTransformer;

    @NonNull
    private final ItemRequirementDefinitionTransformer itemRequirementDefinitionTransformer;

    @NonNull
    private final ItemPriceDefinitionListTransformer itemPriceDefinitionListTransformer;

    @NonNull
    private final EffectDefinitionListTransformer effectDefinitionListTransformer;

    public ItemDefinition transform(RawItemDefinition rawItemDefinition) {
        return ItemDefinition.builder()
                .id(rawItemDefinition.getId())
                .name(rawItemDefinition.getName())
                .type(rawItemDefinition.getType())
                .usable(rawItemDefinition.isUsable())
                .weight(rawItemDefinition.getWeight())
                .equipment(rawItemDefinition.isEquipment())
                .priceDefinitions(itemPriceDefinitionListTransformer.transform(rawItemDefinition.getPriceList()))
                //TODO: use a real item effect definition based on the effectlistdef
                .combatEffects(effectDefinitionListTransformer.transform(rawItemDefinition.getEffectList()))
                .modifiers(itemModifierDefinitionTransformer.transform(rawItemDefinition.getModifiers()))
                .requirements(itemRequirementDefinitionTransformer.transform(rawItemDefinition.getRequirements()))
                .build();
    }
}
