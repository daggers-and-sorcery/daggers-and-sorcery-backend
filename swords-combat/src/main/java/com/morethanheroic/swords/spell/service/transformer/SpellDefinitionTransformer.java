package com.morethanheroic.swords.spell.service.transformer;

import com.morethanheroic.swords.combat.domain.effect.CombatEffectDefinition;
import com.morethanheroic.swords.effect.service.transformer.EffectDefinitionTransformer;
import com.morethanheroic.swords.spell.domain.SkillAttributeRequirementDefinition;
import com.morethanheroic.swords.spell.domain.SpellCost;
import com.morethanheroic.swords.spell.domain.SpellDefinition;
import com.morethanheroic.swords.spell.service.loader.domain.RawSkillAttributeRequirementDefinition;
import com.morethanheroic.swords.spell.service.loader.domain.RawSpellCost;
import com.morethanheroic.swords.spell.service.loader.domain.RawSpellDefinition;
import com.morethanheroic.swords.spell.service.loader.domain.RawSpellEffectDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SpellDefinitionTransformer {

    @Autowired
    private EffectDefinitionTransformer combatEffectDefinitionTransformer;

    @Autowired
    private SkillAttributeRequirementDefinitionTransformer skillAttributeRequirementDefinitionTransformer;

    @Autowired
    private SpellCostTransformer spellCostTransformer;

    public SpellDefinition transform(RawSpellDefinition rawSpellDefinition) {
        return SpellDefinition.builder()
                .id(rawSpellDefinition.getId())
                .name(rawSpellDefinition.getName())
                .combatEffects(transformCombatEffects(rawSpellDefinition.getEffectList()))
                .combatSpell(rawSpellDefinition.isCombatSpell())
                .openPage(rawSpellDefinition.isOpenPage())
                .skillRequirements(transformSkillRequirements(rawSpellDefinition.getSkillRequirements()))
                .spellCosts(transformSpellCosts(rawSpellDefinition.getCostList()))
                .type(rawSpellDefinition.getType())
                .description(rawSpellDefinition.getDescription())
                .spellTarget(rawSpellDefinition.getSpellTarget())
                .build();
    }

    private List<CombatEffectDefinition> transformCombatEffects(List<RawSpellEffectDefinition> effectList) {
        final List<CombatEffectDefinition> result = new ArrayList<>();

        try {
            if (effectList != null) {
                for (RawSpellEffectDefinition effect : effectList) {
                    //TODO: Later this can't only be a combat effect! Be careful with that
                    result.add((CombatEffectDefinition) combatEffectDefinitionTransformer.transform(effect));
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return result;
    }

    private List<SkillAttributeRequirementDefinition> transformSkillRequirements(List<RawSkillAttributeRequirementDefinition> rawSkillAttributeRequirementDefinitionList) {
        if (rawSkillAttributeRequirementDefinitionList == null) {
            return Collections.emptyList();
        }

        return rawSkillAttributeRequirementDefinitionList.stream().map(skillAttributeRequirementDefinitionTransformer::transform).collect(Collectors.toList());
    }

    private List<SpellCost> transformSpellCosts(List<RawSpellCost> rawSpellCostList) {
        if (rawSpellCostList == null) {
            return Collections.emptyList();
        }

        return rawSpellCostList.stream().map(spellCostTransformer::transform).collect(Collectors.toList());
    }
}
