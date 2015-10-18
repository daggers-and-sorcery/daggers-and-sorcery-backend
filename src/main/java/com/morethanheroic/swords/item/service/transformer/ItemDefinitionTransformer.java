package com.morethanheroic.swords.item.service.transformer;

import com.morethanheroic.swords.combat.domain.CombatEffect;
import com.morethanheroic.swords.effect.service.EffectDefinitionBuilder;
import com.morethanheroic.swords.item.domain.ItemDefinition;
import com.morethanheroic.swords.item.service.loader.domain.ItemEffect;
import com.morethanheroic.swords.item.service.loader.domain.RawItemDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class ItemDefinitionTransformer {

    private final EffectDefinitionBuilder effectDefinitionBuilder;
    private final ItemDefinitionModifierListTransformer itemDefinitionModifierListTransformer;
    private final ItemDefinitionRequirementListTransformer itemDefinitionRequirementListTransformer;

    @Autowired
    public ItemDefinitionTransformer(EffectDefinitionBuilder effectDefinitionBuilder, ItemDefinitionModifierListTransformer itemDefinitionModifierListTransformer, ItemDefinitionRequirementListTransformer itemDefinitionRequirementListTransformer) {
        this.effectDefinitionBuilder = effectDefinitionBuilder;
        this.itemDefinitionModifierListTransformer = itemDefinitionModifierListTransformer;
        this.itemDefinitionRequirementListTransformer = itemDefinitionRequirementListTransformer;
    }

    public ItemDefinition transform(RawItemDefinition rawItemDefinition) throws Exception {
        ItemDefinition.ItemDefinitionBuilder itemDefinitionBuilder = new ItemDefinition.ItemDefinitionBuilder();

        itemDefinitionBuilder.setId(rawItemDefinition.getId());
        itemDefinitionBuilder.setName(rawItemDefinition.getName());
        itemDefinitionBuilder.setType(rawItemDefinition.getType());
        itemDefinitionBuilder.setUsable(rawItemDefinition.isUsable());
        itemDefinitionBuilder.setWeight(rawItemDefinition.getWeight());
        itemDefinitionBuilder.setEquipment(rawItemDefinition.isEquipment());

        itemDefinitionBuilder.setCombatEffects(buildEffects(rawItemDefinition.getEffectList()));

        buildModifiers(itemDefinitionBuilder, rawItemDefinition);
        buildRequirements(itemDefinitionBuilder, rawItemDefinition);

        return itemDefinitionBuilder.build();
    }

    private void buildModifiers(ItemDefinition.ItemDefinitionBuilder itemDefinitionBuilder, RawItemDefinition rawItemDefinition) {
        itemDefinitionBuilder.setBasicModifiers(itemDefinitionModifierListTransformer.transformBasicModifier(rawItemDefinition.getBasicModifiers()));
        itemDefinitionBuilder.setCombatModifiers(itemDefinitionModifierListTransformer.transformCombatModifier(rawItemDefinition.getCombatModifiers()));
        itemDefinitionBuilder.setGeneralModifiers(itemDefinitionModifierListTransformer.transformGeneralModifier(rawItemDefinition.getGeneralModifiers()));
        itemDefinitionBuilder.setSkillModifiers(itemDefinitionModifierListTransformer.transformSkillModifier(rawItemDefinition.getSkillModifiers()));
    }

    private void buildRequirements(ItemDefinition.ItemDefinitionBuilder itemDefinitionBuilder, RawItemDefinition rawItemDefinition) {
        itemDefinitionBuilder.setBasicRequirements(itemDefinitionRequirementListTransformer.transformBasicRequirement(rawItemDefinition.getBasicRequirements()));
        itemDefinitionBuilder.setCombatRequirements(itemDefinitionRequirementListTransformer.transformCombatRequirement(rawItemDefinition.getCombatRequirements()));
        itemDefinitionBuilder.setGeneralRequirements(itemDefinitionRequirementListTransformer.transformGeneralRequirement(rawItemDefinition.getGeneralRequirements()));
        itemDefinitionBuilder.setSkillRequirements(itemDefinitionRequirementListTransformer.transformSkillRequirement(rawItemDefinition.getSkillRequirements()));
    }

    private List<CombatEffect> buildEffects(List<ItemEffect> rawEffectList) throws Exception {
        List<CombatEffect> effects = new ArrayList<>();

        if (rawEffectList != null) {
            for (ItemEffect effect : rawEffectList) {
                effects.add(effectDefinitionBuilder.build(effect));
            }
        }

        return Collections.unmodifiableList(effects);
    }
}
