package com.morethanheroic.swords.attribute.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.morethanheroic.swords.attribute.enums.Attribute;
import com.morethanheroic.swords.attribute.enums.AttributeType;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum SkillAttribute implements Attribute {

    TWO_HANDED_CRUSHING_WEAPONS(GeneralAttribute.STRENGTH),
    ONE_HANDED_CRUSHING_WEAPONS(GeneralAttribute.STRENGTH),
    ONE_HANDED_AXES(GeneralAttribute.DEXTERITY),
    TWO_HANDED_AXES(GeneralAttribute.DEXTERITY), 
    HERBLORE(GeneralAttribute.WISDOM), 
    WOODCUTTING(GeneralAttribute.ENDURANCE), 
    ALCHEMY(GeneralAttribute.INTELLIGENCE);

    private final GeneralAttribute incrementedAttribute;

    SkillAttribute(GeneralAttribute incrementedAttribute) {
        this.incrementedAttribute = incrementedAttribute;
    }

    @Override
    public AttributeType getAttributeType() {
        return AttributeType.SKILL;
    }

    @Override
    public int getInitialValue() {
        return 1;
    }

    @Override
    public boolean isUnlimited() {
        return true;
    }

    @Override
    public String getName() {
        return this.name();
    }

    public GeneralAttribute getIncrementedAttribute() {
        return incrementedAttribute;
    }
}
