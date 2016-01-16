package com.morethanheroic.swords.attribute.domain;

import com.morethanheroic.swords.attribute.domain.type.AttributeType;
import com.morethanheroic.swords.attribute.domain.type.GeneralAttributeType;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.WordUtils;

/**
 * The {@link AttributeType#GENERAL} attributes.
 */
@RequiredArgsConstructor
public enum GeneralAttribute implements Attribute {

    //TODO: Load these values from an xml file and use an GeneralAttributeDefinition class.
    STRENGTH(10, true, GeneralAttributeType.PHYSICAL),
    PERCEPTION(10, true, GeneralAttributeType.PHYSICAL),
    DEXTERITY(10, true, GeneralAttributeType.PHYSICAL),
    SWIFTNESS(10, true, GeneralAttributeType.PHYSICAL),
    VITALITY(10, true, GeneralAttributeType.PHYSICAL),
    ENDURANCE(10, true, GeneralAttributeType.PHYSICAL),
    BEAUTY(10, true, GeneralAttributeType.PHYSICAL),

    INTELLIGENCE(10, true, GeneralAttributeType.MENTAL),
    WISDOM(10, true, GeneralAttributeType.MENTAL),
    WILLPOWER(10, true, GeneralAttributeType.MENTAL),
    CHARISMA(10, true, GeneralAttributeType.MENTAL);

    private final int initialValue;
    private final boolean unlimited;
    private final GeneralAttributeType attributeType;

    @Override
    public AttributeType getAttributeType() {
        return AttributeType.GENERAL;
    }

    @Override
    public int getInitialValue() {
        return initialValue;
    }

    @Override
    public boolean isUnlimited() {
        return unlimited;
    }

    @Override
    public String getName() {
        return WordUtils.capitalize(this.name().toLowerCase());
    }

    public GeneralAttributeType getGeneralAttributeType() {
        return attributeType;
    }
}