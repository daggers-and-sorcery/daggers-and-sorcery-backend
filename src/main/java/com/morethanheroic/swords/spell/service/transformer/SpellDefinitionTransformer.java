package com.morethanheroic.swords.spell.service.transformer;

import com.morethanheroic.swords.combat.domain.CombatEffect;
import com.morethanheroic.swords.effect.service.EffectTransformer;
import com.morethanheroic.swords.spell.domain.SkillAttributeRequirementDefinition;
import com.morethanheroic.swords.spell.domain.SpellCost;
import com.morethanheroic.swords.spell.domain.SpellDefinition;
import com.morethanheroic.swords.spell.service.loader.domain.RawSkillAttributeRequirementDefinition;
import com.morethanheroic.swords.spell.service.loader.domain.RawSpellCost;
import com.morethanheroic.swords.spell.service.loader.domain.RawSpellDefinition;
import com.morethanheroic.swords.spell.service.loader.domain.RawSpellEffect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SpellDefinitionTransformer {

    @Autowired
    private EffectTransformer combatEffectTransformer;

    @Autowired
    private SkillAttributeRequirementDefinitionTransformer skillAttributeRequirementDefinitionTransformer;

    @Autowired
    private SpellCostTransformer spellCostTransformer;

    public SpellDefinition transform(RawSpellDefinition rawSpellDefinition) {
        SpellDefinition.SpellDefinitionBuilder spellDefinitionBuilder = new SpellDefinition.SpellDefinitionBuilder();

        spellDefinitionBuilder.setId(rawSpellDefinition.getId());
        spellDefinitionBuilder.setName(rawSpellDefinition.getName());
        spellDefinitionBuilder.setCombatEffects(transformCombatEffects(rawSpellDefinition.getEffectList()));
        spellDefinitionBuilder.setCombatSpell(rawSpellDefinition.isCombatSpell());
        spellDefinitionBuilder.setOpenPage(rawSpellDefinition.isOpenPage());
        spellDefinitionBuilder.setSkillRequirements(transformSkillRequirements(rawSpellDefinition.getSkillRequirements()));
        spellDefinitionBuilder.setSpellCosts(transformSpellCosts(rawSpellDefinition.getCostList()));
        spellDefinitionBuilder.setType(rawSpellDefinition.getType());

        return spellDefinitionBuilder.build();
    }

    private List<CombatEffect> transformCombatEffects(List<RawSpellEffect> effectList) {
        List<CombatEffect> result = new ArrayList<>();

        try {
            if (effectList != null) {
                for (RawSpellEffect effect : effectList) {
                    //TODO: Latr this can't only be a combat effect! Be careful with that
                    result.add((CombatEffect) combatEffectTransformer.build(effect));
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
