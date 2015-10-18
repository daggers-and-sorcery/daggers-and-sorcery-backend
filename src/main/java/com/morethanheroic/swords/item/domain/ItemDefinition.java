package com.morethanheroic.swords.item.domain;

import com.morethanheroic.swords.attribute.domain.modifier.*;
import com.morethanheroic.swords.attribute.domain.requirement.*;
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

    private List<BasicAttributeRequirementDefinition> basicRequirements = Collections.<BasicAttributeRequirementDefinition>emptyList();
    private List<CombatAttributeRequirementDefinition> combatRequirements = Collections.<CombatAttributeRequirementDefinition>emptyList();
    private List<GeneralAttributeRequirementDefinition> generalRequirements = Collections.<GeneralAttributeRequirementDefinition>emptyList();
    private List<SkillAttributeRequirementDefinition> skillRequirements = Collections.<SkillAttributeRequirementDefinition>emptyList();
    private List<AttributeRequirementDefinition> allRequirementsList = Collections.<AttributeRequirementDefinition>emptyList();

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
        return allRequirementsList;
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

        public void setBasicRequirements(List<BasicAttributeRequirementDefinition> basicRequirements) {
            itemDefinition.basicRequirements = basicRequirements;
        }

        public void setCombatRequirements(List<CombatAttributeRequirementDefinition> combatRequirements) {
            itemDefinition.combatRequirements = combatRequirements;
        }

        public void setGeneralRequirements(List<GeneralAttributeRequirementDefinition> generalRequirements) {
            itemDefinition.generalRequirements = generalRequirements;
        }

        public void setSkillRequirements(List<SkillAttributeRequirementDefinition> skillRequirements) {
            itemDefinition.skillRequirements = skillRequirements;
        }

        public ItemDefinition build() {
            buildAllModifiersList();
            buildAllRequirementsList();

            return itemDefinition;
        }

        private void buildAllModifiersList() {
            itemDefinition.allModifiersList = new ArrayList<>();

            itemDefinition.allModifiersList.addAll(itemDefinition.basicModifiers);
            itemDefinition.allModifiersList.addAll(itemDefinition.combatModifiers);
            itemDefinition.allModifiersList.addAll(itemDefinition.generalModifiers);
            itemDefinition.allModifiersList.addAll(itemDefinition.skillModifiers);
        }

        private void buildAllRequirementsList() {
            itemDefinition.allRequirementsList = new ArrayList<>();

            itemDefinition.allRequirementsList.addAll(itemDefinition.basicRequirements);
            itemDefinition.allRequirementsList.addAll(itemDefinition.skillRequirements);
            itemDefinition.allRequirementsList.addAll(itemDefinition.combatRequirements);
            itemDefinition.allRequirementsList.addAll(itemDefinition.generalRequirements);
        }
    }
}
