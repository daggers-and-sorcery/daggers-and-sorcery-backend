package com.morethanheroic.swords.spell.domain;

import com.morethanheroic.swords.combat.domain.CombatEffect;
import com.morethanheroic.swords.spell.service.loader.domain.SkillAttributeRequirementDefinition;
import com.morethanheroic.swords.spell.service.loader.domain.SpellCost;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SpellDefinition {

    private int id;
    private String name;
    private SpellType type;
    private boolean combatSpell;
    private boolean openPage = false;
    private List<CombatEffect> combatEffects;
    private List<SpellCost> spellCosts = Collections.unmodifiableList(new ArrayList<>());
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

    public List<CombatEffect> getCombatEffects() {
        return combatEffects;
    }

    public List<SkillAttributeRequirementDefinition> getSkillRequirements() {
        return skillRequirements;
    }

    public List<SpellCost> getSpellCosts() {
        return spellCosts;
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

        public void setCombatEffects(List<CombatEffect> combatEffects) {
            spellDefinition.combatEffects = combatEffects;
        }

        public void setSpellCosts(List<SpellCost> spellCosts) {
            spellDefinition.spellCosts = spellCosts;
        }

        public void setSkillRequirements(List<SkillAttributeRequirementDefinition> skillRequirements) {
            spellDefinition.skillRequirements = skillRequirements;
        }

        public SpellDefinition build() {
            return spellDefinition;
        }
    }
}
