package com.morethanheroic.swords.attribute.domain;

import com.morethanheroic.swords.attribute.enums.Attribute;
import com.morethanheroic.swords.attribute.enums.AttributeType;

public enum SkillAttribute implements Attribute {

    TWO_HANDED_CRUSHING_WEAPONS(GeneralAttribute.STRENGTH),
    ONE_HANDED_CRUSHING_WEAPONS(GeneralAttribute.STRENGTH),
    TWO_HANDED_AXES(GeneralAttribute.DEXTERITY),
    ONE_HANDED_AXES(GeneralAttribute.DEXTERITY),
    THROWING_WEAPONS(GeneralAttribute.SWIFTNESS),
    FISTFIGHT(GeneralAttribute.ENDURANCE),
    LONGSWORDS(GeneralAttribute.STRENGTH),
    SHORTSWORDS(GeneralAttribute.SWIFTNESS),
    POLEARMS(GeneralAttribute.STRENGTH),
    DAGGERS(GeneralAttribute.SWIFTNESS),
    LONGBOWS(GeneralAttribute.DEXTERITY),
    SHORTBOWS(GeneralAttribute.DEXTERITY),
    CROSSBOWS(GeneralAttribute.DEXTERITY),
    LIGHT_ARMOR(GeneralAttribute.ENDURANCE),
    HEAVY_ARMOR(GeneralAttribute.ENDURANCE),
    ROBE_ARMOR(GeneralAttribute.ENDURANCE),
    ARMORLESS_DEFENSE(GeneralAttribute.ENDURANCE),
    SHIELD_DEFENSE(GeneralAttribute.STRENGTH);

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
