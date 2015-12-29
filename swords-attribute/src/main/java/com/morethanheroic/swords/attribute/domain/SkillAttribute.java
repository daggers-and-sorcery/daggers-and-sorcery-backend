package com.morethanheroic.swords.attribute.domain;

import com.morethanheroic.swords.attribute.domain.type.AttributeType;
import lombok.RequiredArgsConstructor;

/**
 * The {@link AttributeType#SKILL} attributes.
 */
@RequiredArgsConstructor
public enum SkillAttribute implements Attribute {

    //TODO: Load these values from an xml file and use an SkillAttributeDefinition class.
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
    LIGHT_ARMOR(GeneralAttribute.DEXTERITY),
    HEAVY_ARMOR(GeneralAttribute.STRENGTH),
    ROBE_ARMOR(GeneralAttribute.INTELLIGENCE),
    ARMORLESS_DEFENSE(GeneralAttribute.ENDURANCE),
    SHIELD_DEFENSE(GeneralAttribute.STRENGTH),
    STAFF(GeneralAttribute.INTELLIGENCE),
    WAND(GeneralAttribute.WILLPOWER),
    SPECTRE(GeneralAttribute.WISDOM),
    SCAVENGING(GeneralAttribute.PERCEPTION),
    COOKING(GeneralAttribute.VITALITY);

    private final GeneralAttribute incrementedAttribute;

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
