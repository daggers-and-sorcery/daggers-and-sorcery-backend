package com.morethanheroic.swords.item.domain;

import com.morethanheroic.swords.combat.domain.CombatEffect;
import com.morethanheroic.swords.item.service.domain.*;

import java.util.Collections;
import java.util.List;

public class ItemDefinition {

    private int id;
    private String name;
    private ItemType type;
    private boolean usable;
    private int weight;
    private List<CombatEffect> combatEffects;
    private boolean equipment;

    private List<BasicAttributeModifierDefinition> basicModifiers = Collections.<BasicAttributeModifierDefinition>emptyList();
    private List<CombatAttributeModifierDefinition> combatModifiers = Collections.<CombatAttributeModifierDefinition>emptyList();
    private List<GeneralAttributeModifierDefinition> generalModifiers = Collections.<GeneralAttributeModifierDefinition>emptyList();
    private List<SkillAttributeModifierDefinition> skillModifiers = Collections.<SkillAttributeModifierDefinition>emptyList();
    private List<AttributeModifierDefinition> allModifiersList = Collections.<AttributeModifierDefinition>emptyList();

    private List<BasicAttributeRequirementDefinition> basicRequirements = Collections.<BasicAttributeRequirementDefinition>emptyList();
    private List<CombatAttributeRequirementDefinition> combatRequirements = Collections.<CombatAttributeRequirementDefinition>emptyList();
    private List<GeneralAttributeRequirementDefinition> generalRequirements = Collections.<GeneralAttributeRequirementDefinition>emptyList();
    private List<SkillAttributeRequirementDefinition> skillRequirements = Collections.<SkillAttributeRequirementDefinition>emptyList();
    private List<AttributeRequirementDefinition> allRequiremensList = Collections.<AttributeRequirementDefinition>emptyList();

    public ItemDefinition(RawItemDefinition rawItemDefinition, List<CombatEffect> combatEffects) {
        this.id = rawItemDefinition.getId();
        this.name = rawItemDefinition.getName();
        this.type = rawItemDefinition.getType();
        this.usable = rawItemDefinition.isUsable();
        this.weight = rawItemDefinition.getWeight();

        if (rawItemDefinition.getBasicModifiers() != null) {
            basicModifiers = Collections.unmodifiableList(rawItemDefinition.getBasicModifiers());

            allModifiersList.addAll(basicModifiers);
        }

        if (rawItemDefinition.getCombatModifiers() != null) {
            combatModifiers = Collections.unmodifiableList(rawItemDefinition.getCombatModifiers());

            allModifiersList.addAll(combatModifiers);
        }

        if (rawItemDefinition.getGeneralModifiers() != null) {
            generalModifiers = Collections.unmodifiableList(rawItemDefinition.getGeneralModifiers());

            allModifiersList.addAll(generalModifiers);
        }

        if (rawItemDefinition.getSkillModifiers() != null) {
            skillModifiers = Collections.unmodifiableList(rawItemDefinition.getSkillModifiers());

            allModifiersList.addAll(skillModifiers);
        }

        allModifiersList = Collections.unmodifiableList(allModifiersList);

        if (rawItemDefinition.getBasicRequirements() != null) {
            basicRequirements = Collections.unmodifiableList(rawItemDefinition.getBasicRequirements());

            allRequiremensList.addAll(basicRequirements);
        }

        if (rawItemDefinition.getCombatRequirements() != null) {
            combatRequirements = Collections.unmodifiableList(rawItemDefinition.getCombatRequirements());

            allRequiremensList.addAll(combatRequirements);
        }

        if (rawItemDefinition.getGeneralRequirements() != null) {
            generalRequirements = Collections.unmodifiableList(rawItemDefinition.getGeneralRequirements());

            allRequiremensList.addAll(generalRequirements);
        }

        if (rawItemDefinition.getSkillRequirements() != null) {
            skillRequirements = Collections.unmodifiableList(rawItemDefinition.getSkillRequirements());

            allRequiremensList.addAll(skillRequirements);
        }

        allRequiremensList = Collections.unmodifiableList(allRequiremensList);

        this.combatEffects = combatEffects;
        this.equipment = rawItemDefinition.isEquipment();
    }

    public String toString() {
        return "ItemDefinition -> [id: " + id + " name: " + name + "]";
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public ItemType getType() {
        return type;
    }

    public boolean isUsable() {
        return usable;
    }

    public int getWeight() {
        return weight;
    }

    public boolean isEquipment() {
        return equipment;
    }

    public List<BasicAttributeModifierDefinition> getBasicModifiers() {
        return basicModifiers;
    }

    public List<CombatAttributeModifierDefinition> getCombatModifiers() {
        return combatModifiers;
    }

    public List<GeneralAttributeModifierDefinition> getGeneralModifiers() {
        return generalModifiers;
    }

    public List<SkillAttributeModifierDefinition> getSkillModifiers() {
        return skillModifiers;
    }

    public List<AttributeModifierDefinition> getAllModifiers() {
        return allModifiersList;
    }

    public List<SkillAttributeRequirementDefinition> getSkillRequirements() {
        return skillRequirements;
    }

    public List<BasicAttributeRequirementDefinition> getBasicRequirements() {
        return basicRequirements;
    }

    public List<CombatAttributeRequirementDefinition> getCombatRequirements() {
        return combatRequirements;
    }

    public List<GeneralAttributeRequirementDefinition> getGeneralRequirements() {
        return generalRequirements;
    }

    public List<AttributeRequirementDefinition> getAllRequirements() {
        return allRequiremensList;
    }

    public List<CombatEffect> getCombatEffects() {
        return combatEffects;
    }
}
