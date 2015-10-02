package com.morethanheroic.swords.spell.service.domain;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "spell")
@XmlAccessorType(XmlAccessType.FIELD)
public class RawSpellDefinition {

    private int id;
    private String name;
    private SpellType type;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public SpellType getType() {
        return type;
    }
}
