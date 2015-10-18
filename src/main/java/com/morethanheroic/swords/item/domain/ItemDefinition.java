package com.morethanheroic.swords.item.domain;

import com.morethanheroic.swords.attribute.domain.modifier.*;
import com.morethanheroic.swords.combat.domain.CombatEffect;
import com.morethanheroic.swords.item.service.loader.domain.*;

import java.util.ArrayList;
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

    private List<RawBasicAttributeRequirementDefinition> basicRequirements = Collections.<RawBasicAttributeRequirementDefinition>emptyList();
    private List<RawCombatAttributeRequirementDefinition> combatRequirements = Collections.<RawCombatAttributeRequirementDefinition>emptyList();
    private List<GeneralAttributeRequirementDefinition> generalRequirements = Collections.<GeneralAttributeRequirementDefinition>emptyList();
    private List<RawSkillAttributeRequirementDefinition> skillRequirements = Collections.<RawSkillAttributeRequirementDefinition>emptyList();
    private List<RawAttributeRequirementDefinition> allRequiremensList = Collections.<RawAttributeRequirementDefinition>emptyList();

    /*public ItemDefinition(RawItemDefinition rawItemDefinition, List<CombatEffect> combatEffects) {
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
    }*/

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

    public List<RawSkillAttributeRequirementDefinition> getSkillRequirements() {
        return skillRequirements;
    }

    public List<RawBasicAttributeRequirementDefinition> getBasicRequirements() {
        return basicRequirements;
    }

    public List<RawCombatAttributeRequirementDefinition> getCombatRequirements() {
        return combatRequirements;
    }

    public List<GeneralAttributeRequirementDefinition> getGeneralRequirements() {
        return generalRequirements;
    }

    public List<RawAttributeRequirementDefinition> getAllRequirements() {
        return allRequiremensList;
    }

    public List<CombatEffect> getCombatEffects() {
        return combatEffects;
    }

    public static class ItemDefinitionBuilder {

        private final ItemDefinition itemDefinition = new ItemDefinition();

        public void setId(int id) {
            itemDefinition.id = id;
        }

        public void setName(String name) {
            itemDefinition.name = name;
        }

        public void setType(ItemType type) {
            itemDefinition.type = type;
        }

        public void setUsable(boolean usable) {
            itemDefinition.usable = usable;
        }

        public void setWeight(int weight) {
            itemDefinition.weight = weight;
        }

        public void setCombatEffects(List<CombatEffect> combatEffects) {
            itemDefinition.combatEffects = combatEffects;
        }

        public void setEquipment(boolean equipment) {
            itemDefinition.equipment = equipment;
        }

        public void setBasicModifiers(List<BasicAttributeModifierDefinition> basicModifiers) {
            itemDefinition.basicModifiers = basicModifiers;
        }

        public void setCombatModifiers(List<CombatAttributeModifierDefinition> combatModifiers) {
            itemDefinition.combatModifiers = combatModifiers;
        }

        public void setGeneralModifiers(List<GeneralAttributeModifierDefinition> generalModifiers) {
            itemDefinition.generalModifiers = generalModifiers;
        }

        public void setSkillModifiers(List<SkillAttributeModifierDefinition> skillModifiers) {
            itemDefinition.skillModifiers = skillModifiers;
        }

        public void setBasicRequirements(List<RawBasicAttributeRequirementDefinition> basicRequirements) {
            itemDefinition.basicRequirements = basicRequirements;
        }

        public void setCombatRequirements(List<RawCombatAttributeRequirementDefinition> combatRequirements) {
            itemDefinition.combatRequirements = combatRequirements;
        }

        public void setGeneralRequirements(List<GeneralAttributeRequirementDefinition> generalRequirements) {
            itemDefinition.generalRequirements = generalRequirements;
        }

        public void setSkillRequirements(List<RawSkillAttributeRequirementDefinition> skillRequirements) {
            itemDefinition.skillRequirements = skillRequirements;
        }

        public ItemDefinition build() {
            buildAllModifiersList();

            return itemDefinition;
        }

        private void buildAllModifiersList() {
            itemDefinition.allModifiersList = new ArrayList<>();

            itemDefinition.allModifiersList.addAll(itemDefinition.basicModifiers);
            itemDefinition.allModifiersList.addAll(itemDefinition.combatModifiers);
            itemDefinition.allModifiersList.addAll(itemDefinition.generalModifiers);
            itemDefinition.allModifiersList.addAll(itemDefinition.skillModifiers);
        }
    }
}
