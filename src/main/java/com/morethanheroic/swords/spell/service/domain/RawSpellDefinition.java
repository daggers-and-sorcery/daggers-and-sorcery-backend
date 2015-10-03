package com.morethanheroic.swords.spell.service.domain;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;

@XmlRootElement(name = "spell")
@XmlAccessorType(XmlAccessType.FIELD)
public class RawSpellDefinition {

    private int id;
    private String name;
    private SpellType type;

    @XmlElement(name = "combat-spell")
    private boolean combatSpell;

    @XmlElementWrapper(name = "effect-list")
    @XmlElement(name = "effect")
    private ArrayList<SpellEffect> effectList;

    @XmlElementWrapper(name = "cost-list")
    @XmlElement(name = "cost")
    private ArrayList<SpellCost> costList;

    @XmlElementWrapper(name = "skill-requirements")
    @XmlElement(name = "requirement")
    private ArrayList<SkillAttributeRequirementDefinition> skillRequirements;

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

    public ArrayList<SpellEffect> getEffectList() {
        return effectList;
    }

    public ArrayList<SkillAttributeRequirementDefinition> getSkillRequirements() {
        return skillRequirements;
    }

    public ArrayList<SpellCost> getCostList() {
        return costList;
    }
}
