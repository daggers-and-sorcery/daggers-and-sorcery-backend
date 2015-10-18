package com.morethanheroic.swords.item.service.transformer;

import com.morethanheroic.swords.attribute.domain.requirement.BasicAttributeRequirementDefinition;
import com.morethanheroic.swords.attribute.domain.requirement.CombatAttributeRequirementDefinition;
import com.morethanheroic.swords.attribute.domain.requirement.GeneralAttributeRequirementDefinition;
import com.morethanheroic.swords.attribute.domain.requirement.SkillAttributeRequirementDefinition;
import com.morethanheroic.swords.attribute.service.requirement.AttributeRequirementDefinitionTransformerFacade;
import com.morethanheroic.swords.combat.domain.CombatEffect;
import com.morethanheroic.swords.effect.service.EffectDefinitionBuilder;
import com.morethanheroic.swords.item.domain.ItemDefinition;
import com.morethanheroic.swords.item.service.loader.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ItemDefinitionTransformer {

    private final EffectDefinitionBuilder effectDefinitionBuilder;
    private final ItemDefinitionModifierListTransformer itemDefinitionModifierListTransformer;
    private final AttributeRequirementDefinitionTransformerFacade attributeRequirementDefinitionTransformerFacade;

    @Autowired
    public ItemDefinitionTransformer(EffectDefinitionBuilder effectDefinitionBuilder, ItemDefinitionModifierListTransformer itemDefinitionModifierListTransformer, AttributeRequirementDefinitionTransformerFacade attributeRequirementDefinitionTransformerFacade) {
        this.effectDefinitionBuilder = effectDefinitionBuilder;
        this.itemDefinitionModifierListTransformer = itemDefinitionModifierListTransformer;
        this.attributeRequirementDefinitionTransformerFacade = attributeRequirementDefinitionTransformerFacade;
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
        itemDefinitionBuilder.setBasicRequirements(transformBasicRequirement(rawItemDefinition.getBasicRequirements()));
        itemDefinitionBuilder.setCombatRequirements(transformCombatRequirement(rawItemDefinition.getCombatRequirements()));
        itemDefinitionBuilder.setGeneralRequirements(transformGeneralRequirement(rawItemDefinition.getGeneralRequirements()));
        itemDefinitionBuilder.setSkillRequirements(transformSkillRequirement(rawItemDefinition.getSkillRequirements()));
    }

    private List<GeneralAttributeRequirementDefinition> transformGeneralRequirement(List<RawGeneralAttributeRequirementDefinition> rawGeneralAttributeRequirementDefinitionList) {
        if (rawGeneralAttributeRequirementDefinitionList == null) {
            return Collections.emptyList();
        }

        return Collections.unmodifiableList(rawGeneralAttributeRequirementDefinitionList.stream().map(attributeRequirementDefinitionTransformerFacade::transform).collect(Collectors.toList()));
    }

    private List<SkillAttributeRequirementDefinition> transformSkillRequirement(List<RawSkillAttributeRequirementDefinition> rawSkillAttributeRequirementDefinitionList) {
        if (rawSkillAttributeRequirementDefinitionList == null) {
            return Collections.emptyList();
        }

        return Collections.unmodifiableList(rawSkillAttributeRequirementDefinitionList.stream().map(attributeRequirementDefinitionTransformerFacade::transform).collect(Collectors.toList()));
    }

    private List<CombatAttributeRequirementDefinition> transformCombatRequirement(List<RawCombatAttributeRequirementDefinition> rawCombatAttributeRequirementDefinitionList) {
        if (rawCombatAttributeRequirementDefinitionList == null) {
            return Collections.emptyList();
        }

        return Collections.unmodifiableList(rawCombatAttributeRequirementDefinitionList.stream().map(attributeRequirementDefinitionTransformerFacade::transform).collect(Collectors.toList()));
    }

    private List<BasicAttributeRequirementDefinition> transformBasicRequirement(List<RawBasicAttributeRequirementDefinition> rawBasicAttributeRequirementDefinitionList) {
        if (rawBasicAttributeRequirementDefinitionList == null) {
            return Collections.emptyList();
        }

        return Collections.unmodifiableList(rawBasicAttributeRequirementDefinitionList.stream().map(attributeRequirementDefinitionTransformerFacade::transform).collect(Collectors.toList()));
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
