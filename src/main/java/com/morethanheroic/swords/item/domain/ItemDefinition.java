package com.morethanheroic.swords.item.domain;

import com.morethanheroic.swords.attribute.domain.requirement.*;
import com.morethanheroic.swords.combat.domain.effect.CombatEffect;
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
    private List<ItemPriceDefinition> itemPriceDefinitions;

    private List<ItemModifierDefinition> modifiers;

    private List<BasicAttributeRequirementDefinition> basicRequirements = Collections.<BasicAttributeRequirementDefinition>emptyList();
    private List<CombatAttributeRequirementDefinition> combatRequirements = Collections.<CombatAttributeRequirementDefinition>emptyList();
    private List<GeneralAttributeRequirementDefinition> generalRequirements = Collections.<GeneralAttributeRequirementDefinition>emptyList();
    private List<SkillAttributeRequirementDefinition> skillRequirements = Collections.<SkillAttributeRequirementDefinition>emptyList();
    private List<AttributeRequirementDefinition> allRequirements = Collections.<AttributeRequirementDefinition>emptyList();

    public boolean isTradeable() {
        return itemPriceDefinitions.size() > 0 || type == ItemType.COIN;
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

        public void setModifiers(List<ItemModifierDefinition> modifiers) {
            itemDefinition.modifiers = modifiers;
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

        public void setPriceDefinition(List<ItemPriceDefinition> itemPriceDefinitions) {
            itemDefinition.itemPriceDefinitions = itemPriceDefinitions;
        }

        public ItemDefinition build() {
            buildAllRequirementsList();

            return itemDefinition;
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
