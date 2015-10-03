package com.morethanheroic.swords.spell.service.domain;

import com.morethanheroic.swords.combat.domain.CombatEffect;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SpellDefinition {

    private final int id;
    private final String name;
    private final SpellType type;
    private final boolean combatSpell;
    private List<CombatEffect> combatEffects;
    private List<SkillAttributeRequirementDefinition> skillRequirements = Collections.unmodifiableList(new ArrayList<>());

    public SpellDefinition(RawSpellDefinition rawSpellDefinition, List<CombatEffect> combatEffects) {
        this.id = rawSpellDefinition.getId();
        this.name = rawSpellDefinition.getName();
        this.type = rawSpellDefinition.getType();
        this.combatSpell = rawSpellDefinition.isCombatSpell();
        this.combatEffects = combatEffects;

        if (rawSpellDefinition.getSkillRequirements() != null) {
            skillRequirements = Collections.unmodifiableList(rawSpellDefinition.getSkillRequirements());
        }
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public SpellType getType() {
        return type;
    }

    public boolean isCombatSpell() {
        return combatSpell;
    }

    public List<CombatEffect> getCombatEffects() {
        return combatEffects;
    }

    public List<SkillAttributeRequirementDefinition> getSkillRequirements() {
        return skillRequirements;
    }
}
