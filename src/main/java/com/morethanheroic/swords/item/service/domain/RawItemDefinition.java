package com.morethanheroic.swords.item.service.domain;

import com.morethanheroic.swords.equipment.domain.EquipmentSlot;
import com.morethanheroic.swords.item.domain.ItemType;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@XmlRootElement(name = "item")
@XmlAccessorType(XmlAccessType.FIELD)
public class RawItemDefinition {

    private int id;
    private String name;
    private ItemType type;
    private boolean usable;
    private int weight;

    @XmlElementWrapper(name = "basic-modifiers")
    @XmlElement(name = "modifier")
    private ArrayList<BasicAttributeModifierDefinition> basicModifiers;

    @XmlElementWrapper(name = "combat-modifiers")
    @XmlElement(name = "modifier")
    private ArrayList<CombatAttributeModifierDefinition> combatModifiers;

    @XmlElementWrapper(name = "general-modifiers")
    @XmlElement(name = "modifier")
    private ArrayList<GeneralAttributeModifierDefinition> generalModifiers;

    @XmlElementWrapper(name = "skill-modifiers")
    @XmlElement(name = "modifier")
    private ArrayList<SkillAttributeModifierDefinition> skillModifiers;

    @XmlElementWrapper(name = "basic-requirements")
    @XmlElement(name = "requirement")
    private ArrayList<BasicAttributeRequirementDefinition> basicRequirements;

    @XmlElementWrapper(name = "combat-requirements")
    @XmlElement(name = "requirement")
    private ArrayList<CombatAttributeRequirementDefinition> combatRequirements;

    @XmlElementWrapper(name = "general-requirements")
    @XmlElement(name = "requirement")
    private ArrayList<GeneralAttributeRequirementDefinition> generalRequirements;

    @XmlElementWrapper(name = "skill-requirements")
    @XmlElement(name = "requirement")
    private ArrayList<SkillAttributeRequirementDefinition> skillRequirements;

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
        List<AttributeRequirementDefinition> list = new ArrayList<>();

        if (basicRequirements != null) {
            list.addAll(basicRequirements);
        }
        if (combatRequirements != null) {
            list.addAll(combatRequirements);
        }
        if (generalRequirements != null) {
            list.addAll(generalRequirements);
        }
        if (skillRequirements != null) {
            list.addAll(skillRequirements);
        }

        return Collections.unmodifiableList(list);
    }
}
