package com.morethanheroic.swords.spell.domain;

import java.util.List;

import com.morethanheroic.swords.effect.domain.EffectSettingDefinitionHolder;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class SpellDefinition {

    private int id;
    private String name;
    private String description;
    private SpellTarget spellTarget;
    private SpellType type;
    private boolean combatSpell;
    private boolean openPage;
    private List<EffectSettingDefinitionHolder> combatEffects;
    private List<SpellCost> spellCosts;
    private List<SkillAttributeRequirementDefinition> skillRequirements;
}
