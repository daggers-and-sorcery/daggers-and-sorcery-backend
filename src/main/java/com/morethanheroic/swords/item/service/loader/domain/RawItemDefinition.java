package com.morethanheroic.swords.item.service.loader.domain;

import com.morethanheroic.swords.item.domain.ItemType;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "item")
@XmlAccessorType(XmlAccessType.FIELD)
public class RawItemDefinition {

    private int id;
    private String name;
    private ItemType type;
    private boolean usable;
    private int weight;
    private boolean equipment;

    @XmlElementWrapper(name = "use-effect-list")
    @XmlElement(name = "effect")
    private ArrayList<ItemEffect> effectList;

    @XmlElementWrapper(name = "basic-modifiers")
    @XmlElement(name = "modifier")
    private ArrayList<RawBasicAttributeModifierDefinition> basicModifiers;

    @XmlElementWrapper(name = "combat-modifiers")
    @XmlElement(name = "modifier")
    private ArrayList<RawCombatAttributeModifierDefinition> combatModifiers;

    @XmlElementWrapper(name = "general-modifiers")
    @XmlElement(name = "modifier")
    private ArrayList<RawGeneralAttributeModifierDefinition> generalModifiers;

    @XmlElementWrapper(name = "skill-modifiers")
    @XmlElement(name = "modifier")
    private ArrayList<RawSkillAttributeModifierDefinition> skillModifiers;

    @XmlElementWrapper(name = "basic-requirements")
    @XmlElement(name = "requirement")
    private ArrayList<RawBasicAttributeRequirementDefinition> basicRequirements;

    @XmlElementWrapper(name = "combat-requirements")
    @XmlElement(name = "requirement")
    private ArrayList<RawCombatAttributeRequirementDefinition> combatRequirements;

    @XmlElementWrapper(name = "general-requirements")
    @XmlElement(name = "requirement")
    private ArrayList<GeneralAttributeRequirementDefinition> generalRequirements;

    @XmlElementWrapper(name = "skill-requirements")
    @XmlElement(name = "requirement")
    private ArrayList<RawSkillAttributeRequirementDefinition> skillRequirements;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public ItemType getType() {
        return type;
    }

    public int getWeight() {
        return weight;
    }

    public boolean isUsable() {
        return usable;
    }

    public String toString() {
        return "RawItemDefinition -> [id: " + id + " name: " + name + "]";
    }

    public List<RawBasicAttributeModifierDefinition> getBasicModifiers() {
        return basicModifiers;
    }

    public List<RawCombatAttributeModifierDefinition> getCombatModifiers() {
        return combatModifiers;
    }

    public List<RawGeneralAttributeModifierDefinition> getGeneralModifiers() {
        return generalModifiers;
    }

    public List<RawSkillAttributeModifierDefinition> getSkillModifiers() {
        return skillModifiers;
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

    public ArrayList<ItemEffect> getEffectList() {
        return effectList;
    }

    public boolean isEquipment() {
        return equipment;
    }
}
