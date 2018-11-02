package com.morethanheroic.swords.attribute.domain;

import com.morethanheroic.swords.attribute.domain.type.AttributeType;
import org.apache.commons.text.WordUtils;

import java.util.Locale;

/**
 * The {@link AttributeType#BASIC} attributes.
 */
public enum BasicAttribute implements Attribute {

    //TODO: Load these values from an xml file and use a BasicAttributeDefinition class.
    MOVEMENT(30, false),
    HEALTH_REGENERATION(2, true),
    MANA_REGENERATION(2, true),
    MOVEMENT_REGENERATION(1, true),
    SCAVENGING_BONUS(0, true);

    private final int initialValue;
    private final boolean unlimited;
    private final String name;

    BasicAttribute(int initialValue, boolean unlimited) {
        this.initialValue = initialValue;
        this.unlimited = unlimited;

        this.name = WordUtils.capitalize(this.name().replace("_", " ").toLowerCase(Locale.ENGLISH));
    }

    @Override
    public AttributeType getAttributeType() {
        return AttributeType.BASIC;
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
        return name;
    }
}
