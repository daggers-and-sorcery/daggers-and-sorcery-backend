package com.morethanheroic.swords.spell.domain;

import com.morethanheroic.swords.combat.domain.effect.CombatEffectDefinition;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SpellDefinition {

    private int id;
    private String name;

    @Getter
    private String description;

    private SpellType type;
    private boolean combatSpell;
    private boolean openPage = false;
    private List<CombatEffectDefinition> combatEffects;
    private List<SpellCost> rawSpellCosts = Collections.unmodifiableList(new ArrayList<>());
    private List<SkillAttributeRequirementDefinition> skillRequirements = Collections.unmodifiableList(new ArrayList<>());

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

    public List<CombatEffectDefinition> getCombatEffects() {
        return combatEffects;
    }

    public List<SkillAttributeRequirementDefinition> getSkillRequirements() {
        return skillRequirements;
    }

    public List<SpellCost> getSpellCosts() {
        return rawSpellCosts;
    }

    public boolean isOpenPage() {
        return openPage;
    }

    public static class SpellDefinitionBuilder {

        private final SpellDefinition spellDefinition = new SpellDefinition();

        public void setId(int id) {
            spellDefinition.id = id;
        }

        public void setName(String name) {
            spellDefinition.name = name;
        }

        public void setType(SpellType type) {
            spellDefinition.type = type;
        }

        public void setCombatSpell(boolean combatSpell) {
            spellDefinition.combatSpell = combatSpell;
        }

        public void setOpenPage(boolean openPage) {
            spellDefinition.openPage = openPage;
        }

        public void setCombatEffects(List<CombatEffectDefinition> combatEffects) {
            spellDefinition.combatEffects = combatEffects;
        }

        public void setSpellCosts(List<SpellCost> rawSpellCosts) {
            spellDefinition.rawSpellCosts = rawSpellCosts;
        }

        public void setSkillRequirements(List<SkillAttributeRequirementDefinition> skillRequirements) {
            spellDefinition.skillRequirements = skillRequirements;
        }

        public void setDescription(String description) {
            spellDefinition.description = description;
        }

        public SpellDefinition build() {
            return spellDefinition;
        }
    }
}
