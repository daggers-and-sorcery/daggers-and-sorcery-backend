package com.morethanheroic.swords.spell.service.transformer;

import com.morethanheroic.swords.combat.domain.CombatEffect;
import com.morethanheroic.swords.effect.service.CombatEffectTransformer;
import com.morethanheroic.swords.spell.domain.SpellDefinition;
import com.morethanheroic.swords.spell.service.loader.domain.RawSpellDefinition;
import com.morethanheroic.swords.spell.service.loader.domain.SkillAttributeRequirementDefinition;
import com.morethanheroic.swords.spell.service.loader.domain.SpellCost;
import com.morethanheroic.swords.spell.service.loader.domain.SpellEffect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class SpellDefinitionTransformer {

    @Autowired
    private CombatEffectTransformer combatEffectTransformer;

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

    private List<CombatEffect> transformCombatEffects(List<SpellEffect> effectList) {
        List<CombatEffect> result = new ArrayList<>();

        try {
            if (effectList != null) {
                for (SpellEffect effect : effectList) {
                    result.add(combatEffectTransformer.build(effect));
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return result;
    }

    //TODO: basic mocks unti we don't have def builders for them
    private List<SkillAttributeRequirementDefinition> transformSkillRequirements(List<SkillAttributeRequirementDefinition> skillAttributeRequirementDefinitionList) {
        if (skillAttributeRequirementDefinitionList == null) {
            return Collections.emptyList();
        }

        return skillAttributeRequirementDefinitionList;
    }

    private List<SpellCost> transformSpellCosts(List<SpellCost> spellCostList) {
        if (spellCostList == null) {
            return Collections.emptyList();
        }

        return spellCostList;
    }
}
