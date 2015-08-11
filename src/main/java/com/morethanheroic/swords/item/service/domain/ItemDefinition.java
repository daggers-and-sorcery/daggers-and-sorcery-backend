package com.morethanheroic.swords.item.service.domain;

import com.morethanheroic.swords.attribute.domain.BasicAttribute;
import com.morethanheroic.swords.attribute.domain.CombatAttribute;
import com.morethanheroic.swords.attribute.domain.GeneralAttribute;
import com.morethanheroic.swords.attribute.domain.SkillAttribute;
import com.morethanheroic.swords.attribute.enums.Attribute;
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
    private ArrayList<BasicAttribute> basicModifiers;

    @XmlElementWrapper(name = "combat-modifiers")
    @XmlElement(name = "modifier")
    private ArrayList<CombatAttribute> combatModifiers;

    @XmlElementWrapper(name = "general-modifiers")
    @XmlElement(name = "modifier")
    private ArrayList<GeneralAttribute> generalModifiers;

    @XmlElementWrapper(name = "skill-modifiers")
    @XmlElement(name = "modifier")
    private ArrayList<SkillAttribute> skillModifiers;

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
            case SHIELD:
            case ONE_HANDED_SWORD:
                return true;
            default:
                return false;
        }
    }

    public List<BasicAttribute> getBasicModifiers() {
        return Collections.unmodifiableList(basicModifiers);
    }

    public List<CombatAttribute> getCombatModifiers() {
        return Collections.unmodifiableList(combatModifiers);
    }

    public List<GeneralAttribute> getGeneralModifiers() {
        return Collections.unmodifiableList(generalModifiers);
    }

    public List<SkillAttribute> getSkillModifiers() {
        return Collections.unmodifiableList(skillModifiers);
    }

    public List<Attribute> getAllModifiers() {
        List<Attribute> list = new ArrayList<>();

        list.addAll(basicModifiers);
        list.addAll(combatModifiers);
        list.addAll(generalModifiers);
        list.addAll(skillModifiers);

        return Collections.unmodifiableList(list);
    }
}
