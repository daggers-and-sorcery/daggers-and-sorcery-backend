package com.morethanheroic.swords.item.domain;

import com.morethanheroic.swords.attribute.domain.modifier.*;
import com.morethanheroic.swords.attribute.domain.requirement.*;
import com.morethanheroic.swords.combat.domain.CombatEffect;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Getter
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
    private List<AttributeModifierDefinition> allModifiers = Collections.<AttributeModifierDefinition>emptyList();

    private List<BasicAttributeRequirementDefinition> basicRequirements = Collections.<BasicAttributeRequirementDefinition>emptyList();
    private List<CombatAttributeRequirementDefinition> combatRequirements = Collections.<CombatAttributeRequirementDefinition>emptyList();
    private List<GeneralAttributeRequirementDefinition> generalRequirements = Collections.<GeneralAttributeRequirementDefinition>emptyList();
    private List<SkillAttributeRequirementDefinition> skillRequirements = Collections.<SkillAttributeRequirementDefinition>emptyList();
    private List<AttributeRequirementDefinition> allRequirements = Collections.<AttributeRequirementDefinition>emptyList();

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
            itemDefinition.allModifiers = new ArrayList<>();

            itemDefinition.allModifiers.addAll(itemDefinition.basicModifiers);
            itemDefinition.allModifiers.addAll(itemDefinition.combatModifiers);
            itemDefinition.allModifiers.addAll(itemDefinition.generalModifiers);
            itemDefinition.allModifiers.addAll(itemDefinition.skillModifiers);
        }

        private void buildAllRequirementsList() {
            itemDefinition.allRequirements = new ArrayList<>();

            itemDefinition.allRequirements.addAll(itemDefinition.basicRequirements);
            itemDefinition.allRequirements.addAll(itemDefinition.skillRequirements);
            itemDefinition.allRequirements.addAll(itemDefinition.combatRequirements);
            itemDefinition.allRequirements.addAll(itemDefinition.generalRequirements);
        }
    }
}
