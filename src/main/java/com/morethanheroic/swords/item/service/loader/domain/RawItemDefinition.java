package com.morethanheroic.swords.item.service.loader.domain;

import com.morethanheroic.swords.item.domain.ItemType;
import lombok.Getter;

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

    @XmlElementWrapper(name = "price")
    @XmlElement(name = "price-entry")
    @Getter
    private ArrayList<RawItemPriceDefinition> priceList;

    @XmlElementWrapper(name = "use-effect-list")
    @XmlElement(name = "effect")
    private ArrayList<ItemEffect> effectList;

    @XmlElementWrapper(name = "modifiers")
    @XmlElement(name = "modifier")
    private ArrayList<RawItemModifierDefinition> modifiers;

    @XmlElementWrapper(name = "requirements")
    @XmlElement(name = "requirement")
    private ArrayList<RawItemRequirementDefinition> requirements;

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

    public List<RawItemModifierDefinition> getModifiers() {
        return modifiers;
    }

    public List<RawItemRequirementDefinition> getRequirements() {
        return requirements;
    }

    public ArrayList<ItemEffect> getEffectList() {
        return effectList;
    }

    public boolean isEquipment() {
        return equipment;
    }
}
