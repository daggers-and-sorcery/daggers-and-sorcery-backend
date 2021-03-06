package com.morethanheroic.swords.spell.service.loader.domain;

import com.morethanheroic.swords.spell.domain.SpellTarget;
import com.morethanheroic.swords.spell.domain.SpellType;
import lombok.Getter;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;

@XmlRootElement(name = "spell")
@XmlAccessorType(XmlAccessType.FIELD)
public class RawSpellDefinition {

    private int id;
    private String name;
    private SpellType type;

    @Getter
    @XmlElement(name = "spell-target")
    private SpellTarget spellTarget;

    @Getter
    private String description;

    @XmlElement(name = "combat-spell")
    private boolean combatSpell;

    @XmlElement(name = "open-page")
    private boolean openPage;

    @XmlElementWrapper(name = "effect-list")
    @XmlElement(name = "effect")
    private ArrayList<RawSpellEffectDefinition> effectList;

    @XmlElementWrapper(name = "cost-list")
    @XmlElement(name = "cost")
    private ArrayList<RawSpellCost> costList;

    @XmlElementWrapper(name = "skill-requirements")
    @XmlElement(name = "requirement")
    private ArrayList<RawSkillAttributeRequirementDefinition> skillRequirements;

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

    public ArrayList<RawSpellEffectDefinition> getEffectList() {
        return effectList;
    }

    public ArrayList<RawSkillAttributeRequirementDefinition> getSkillRequirements() {
        return skillRequirements;
    }

    public ArrayList<RawSpellCost> getCostList() {
        return costList;
    }

    public boolean isOpenPage() {
        return openPage;
    }
}
