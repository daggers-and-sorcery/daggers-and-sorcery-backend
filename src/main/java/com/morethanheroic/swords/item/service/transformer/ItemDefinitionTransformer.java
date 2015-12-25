package com.morethanheroic.swords.item.service.transformer;

import com.morethanheroic.swords.effect.domain.Effect;
import com.morethanheroic.swords.effect.exception.InvalidEffectException;
import com.morethanheroic.swords.effect.service.EffectTransformer;
import com.morethanheroic.swords.item.domain.ItemDefinition;
import com.morethanheroic.swords.item.domain.ItemPriceDefinition;
import com.morethanheroic.swords.item.service.loader.domain.RawItemEffect;
import com.morethanheroic.swords.item.service.loader.domain.RawItemDefinition;
import com.morethanheroic.swords.item.service.loader.domain.RawItemPriceDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class ItemDefinitionTransformer {

    @Autowired
    private EffectTransformer combatEffectTransformer;

    @Autowired
    private ItemModifierDefinitionTransformer itemModifierDefinitionTransformer;

    @Autowired
    private ItemRequirementDefinitionTransformer itemRequirementDefinitionTransformer;

    public ItemDefinition transform(RawItemDefinition rawItemDefinition) {
        final ItemDefinition.ItemDefinitionBuilder itemDefinitionBuilder = new ItemDefinition.ItemDefinitionBuilder();

        itemDefinitionBuilder.setId(rawItemDefinition.getId());
        itemDefinitionBuilder.setName(rawItemDefinition.getName());
        itemDefinitionBuilder.setType(rawItemDefinition.getType());
        itemDefinitionBuilder.setUsable(rawItemDefinition.isUsable());
        itemDefinitionBuilder.setWeight(rawItemDefinition.getWeight());
        itemDefinitionBuilder.setEquipment(rawItemDefinition.isEquipment());

        itemDefinitionBuilder.setPriceDefinition(transformPriceList(rawItemDefinition.getPriceList()));

        itemDefinitionBuilder.setCombatEffects(buildEffects(rawItemDefinition.getEffectList()));

        itemDefinitionBuilder.setModifiers(itemModifierDefinitionTransformer.transform(rawItemDefinition.getModifiers()));
        itemDefinitionBuilder.setRequirements(itemRequirementDefinitionTransformer.transform(rawItemDefinition.getRequirements()));

        return itemDefinitionBuilder.build();
    }

    private List<Effect> buildEffects(List<RawItemEffect> rawEffectList) {
        try {
            final List<Effect> effects = new ArrayList<>();

            if (rawEffectList != null) {
                for (RawItemEffect effect : rawEffectList) {
                    effects.add(combatEffectTransformer.transform(effect));
                }
            }

            return Collections.unmodifiableList(effects);
        } catch (InvalidEffectException ex) {
            throw new RuntimeException(ex);
        }
    }

    private List<ItemPriceDefinition> transformPriceList(List<RawItemPriceDefinition> rawItemPriceDefinitions) {
        if (rawItemPriceDefinitions == null) {
            return Collections.emptyList();
        }

        final List<ItemPriceDefinition> result = new ArrayList<>();

        for (RawItemPriceDefinition rawItemPriceDefinition : rawItemPriceDefinitions) {
            result.add(transform(rawItemPriceDefinition));
        }

        return Collections.unmodifiableList(result);
    }

    private ItemPriceDefinition transform(RawItemPriceDefinition rawItemPriceDefinition) {
        return ItemPriceDefinition.builder()
                .type(rawItemPriceDefinition.getType())
                .amount(rawItemPriceDefinition.getAmount())
                .build();
    }
}
