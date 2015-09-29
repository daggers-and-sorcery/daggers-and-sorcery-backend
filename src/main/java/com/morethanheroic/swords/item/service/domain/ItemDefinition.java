package com.morethanheroic.swords.item.service.domain;

import com.morethanheroic.swords.item.domain.ItemType;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ItemDefinition {

    private int id;
    private String name;
    private ItemType type;
    private boolean usable;
    private int weight;

    private List<BasicAttributeModifierDefinition> basicModifiers = Collections.unmodifiableList(new ArrayList<>());
    private List<CombatAttributeModifierDefinition> combatModifiers = Collections.unmodifiableList(new ArrayList<>());
    private List<GeneralAttributeModifierDefinition> generalModifiers = Collections.unmodifiableList(new ArrayList<>());
    private List<SkillAttributeModifierDefinition> skillModifiers = Collections.unmodifiableList(new ArrayList<>());
    private List<AttributeModifierDefinition> allModifiersList = new ArrayList<>();

    private List<BasicAttributeRequirementDefinition> basicRequirements = Collections.unmodifiableList(new ArrayList<>());
    private List<CombatAttributeRequirementDefinition> combatRequirements = Collections.unmodifiableList(new ArrayList<>());
    private List<GeneralAttributeRequirementDefinition> generalRequirements = Collections.unmodifiableList(new ArrayList<>());
    private List<SkillAttributeRequirementDefinition> skillRequirements = Collections.unmodifiableList(new ArrayList<>());
    private List<AttributeRequirementDefinition> allRequiremensList = new ArrayList<>();

    public ItemDefinition(RawItemDefinition rawItemDefinition) {
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
        switch (type) {
            case TWO_HANDED_CRUSHING_WEAPONS:
            case ONE_HANDED_CRUSHING_WEAPONS:
            case TWO_HANDED_AXES:
            case ONE_HANDED_AXES:
            case THROWING_WEAPONS:
            case LONGSWORDS:
            case SHORTSWORDS:
            case POLEARMS:
            case DAGGERS:
            case LONGBOWS:
            case SHORTBOWS:
            case CROSSBOWS:
            case SHIELD:
            case LIGHT_ARMOR:
            case HEAVY_ARMOR:
            case ROBE_ARMOR:
                return true;
            default:
                return false;
        }
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
}
