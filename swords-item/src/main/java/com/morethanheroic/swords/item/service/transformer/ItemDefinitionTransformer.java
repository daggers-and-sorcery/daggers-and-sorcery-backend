package com.morethanheroic.swords.item.service.transformer;

import com.google.common.collect.ImmutableList;
import com.morethanheroic.swords.effect.domain.Effect;
import com.morethanheroic.swords.effect.exception.InvalidEffectException;
import com.morethanheroic.swords.effect.service.EffectTransformer;
import com.morethanheroic.swords.item.domain.ItemDefinition;
import com.morethanheroic.swords.item.domain.ItemPriceDefinition;
import com.morethanheroic.swords.item.service.loader.domain.RawItemDefinition;
import com.morethanheroic.swords.item.service.loader.domain.RawItemEffect;
import com.morethanheroic.swords.item.service.loader.domain.RawItemPriceDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;

@Service
public class ItemDefinitionTransformer {

    @Autowired
    private EffectTransformer combatEffectTransformer;

    @Autowired
    private ItemModifierDefinitionTransformer itemModifierDefinitionTransformer;

    @Autowired
    private ItemRequirementDefinitionTransformer itemRequirementDefinitionTransformer;

    public ItemDefinition transform(RawItemDefinition rawItemDefinition) {
        return ItemDefinition.builder()
                .id(rawItemDefinition.getId())
                .name(rawItemDefinition.getName())
                .type(rawItemDefinition.getType())
                .usable(rawItemDefinition.isUsable())
                .weight(rawItemDefinition.getWeight())
                .equipment(rawItemDefinition.isEquipment())
                .priceDefinitions(transformPriceList(rawItemDefinition.getPriceList()))
                .combatEffects(buildEffects(rawItemDefinition.getEffectList()))
                .modifiers(itemModifierDefinitionTransformer.transform(rawItemDefinition.getModifiers()))
                .requirements(itemRequirementDefinitionTransformer.transform(rawItemDefinition.getRequirements()))
                .build();
    }

    //TODO: create own transformer!
    private List<Effect> buildEffects(List<RawItemEffect> rawEffectList) {
        try {
            final List<Effect> effects = new ArrayList<>();

            if (rawEffectList != null) {
                for (RawItemEffect effect : rawEffectList) {
                    effects.add(combatEffectTransformer.transform(effect));
                }
            }

            return ImmutableList.copyOf(effects);
        } catch (InvalidEffectException ex) {
            throw new RuntimeException(ex);
        }
    }

    //TODO: create own transformer service!
    private List<ItemPriceDefinition> transformPriceList(List<RawItemPriceDefinition> rawItemPriceDefinitions) {
        if (rawItemPriceDefinitions == null) {
            return Collections.emptyList();
        }

        return rawItemPriceDefinitions.stream().map(this::transform).collect(collectingAndThen(toList(), ImmutableList::copyOf));
    }

    private ItemPriceDefinition transform(RawItemPriceDefinition rawItemPriceDefinition) {
        return ItemPriceDefinition.builder()
                .type(rawItemPriceDefinition.getType())
                .amount(rawItemPriceDefinition.getAmount())
                .build();
    }
}
