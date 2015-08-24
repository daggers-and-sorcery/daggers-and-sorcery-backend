package com.morethanheroic.swords.item.service.domain;

import com.morethanheroic.swords.equipment.domain.EquipmentSlot;
import com.morethanheroic.swords.item.domain.ItemType;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@XmlRootElement(name = "item")
@XmlAccessorType(XmlAccessType.FIELD)
public class ItemDefinition {

    private int id;
    private String name;
    private ItemType type;
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

    public String toString() {
        return "ItemDefinition -> [id: " + id + " name: " + name + "]";
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
        if (basicModifiers != null) {
            return Collections.unmodifiableList(basicModifiers);
        } else {
            return new ArrayList<>();
        }
    }

    public List<CombatAttributeModifierDefinition> getCombatModifiers() {
        if (combatModifiers != null) {
            return Collections.unmodifiableList(combatModifiers);
        } else {
            return new ArrayList<>();
        }
    }

    public List<GeneralAttributeModifierDefinition> getGeneralModifiers() {
        if (generalModifiers != null) {
            return Collections.unmodifiableList(generalModifiers);
        } else {
            return new ArrayList<>();
        }
    }

    public List<SkillAttributeModifierDefinition> getSkillModifiers() {
        if (skillModifiers != null) {
            return Collections.unmodifiableList(skillModifiers);
        } else {
            return new ArrayList<>();
        }
    }

    public List<AttributeModifierDefinition> getAllModifiers() {
        List<AttributeModifierDefinition> list = new ArrayList<>();

        if (basicModifiers != null) {
            list.addAll(basicModifiers);
        }
        if (combatModifiers != null) {
            list.addAll(combatModifiers);
        }
        if (generalModifiers != null) {
            list.addAll(generalModifiers);
        }
        if (skillModifiers != null) {
            list.addAll(skillModifiers);
        }

        return Collections.unmodifiableList(list);
    }

    public List<SkillAttributeRequirementDefinition> getSkillRequirements() {
        if (skillRequirements != null) {
            return Collections.unmodifiableList(skillRequirements);
        } else {
            return new ArrayList<>();
        }
    }

    public List<BasicAttributeRequirementDefinition> getBasicRequirements() {
        if (basicRequirements != null) {
            return Collections.unmodifiableList(basicRequirements);
        } else {
            return new ArrayList<>();
        }
    }

    public List<CombatAttributeRequirementDefinition> getCombatRequirements() {
        if (combatRequirements != null) {
            return Collections.unmodifiableList(combatRequirements);
        } else {
            return new ArrayList<>();
        }
    }

    public List<GeneralAttributeRequirementDefinition> getGeneralRequirements() {
        if (generalRequirements != null) {
            return Collections.unmodifiableList(generalRequirements);
        } else {
            return new ArrayList<>();
        }
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
