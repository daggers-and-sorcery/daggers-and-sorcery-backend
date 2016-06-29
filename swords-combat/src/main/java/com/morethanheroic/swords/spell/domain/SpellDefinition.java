package com.morethanheroic.swords.spell.domain;

import com.morethanheroic.swords.combat.domain.effect.CombatEffectDefinition;
import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
    private List<CombatEffectDefinition> combatEffects;
    private List<SpellCost> spellCosts;
    private List<SkillAttributeRequirementDefinition> skillRequirements;
}
