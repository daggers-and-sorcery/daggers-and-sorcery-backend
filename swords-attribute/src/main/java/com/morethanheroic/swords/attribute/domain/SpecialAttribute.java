package com.morethanheroic.swords.attribute.domain;

import com.morethanheroic.swords.attribute.domain.type.AttributeType;
import org.apache.commons.text.WordUtils;

import java.util.Locale;

/**
 * The {@link AttributeType#SPECIAL} attributes.
 */
public enum SpecialAttribute implements Attribute {

    LIFE_STEAL,
    EXTRA_DAMAGE_AGAINST_VAMPIRES,
    PERIODICAL_DAMAGE_AGAINST_VAMPIRES,
    PERIODICAL_DAMAGE_AGAINST_UNDEAD;

    private final String name;

    SpecialAttribute() {
        this.name = WordUtils.capitalize(this.name().replace("_", " ").toLowerCase(Locale.ENGLISH));
    }

    @Override
    public AttributeType getAttributeType() {
        return AttributeType.SPECIAL;
    }

    @Override
    public int getInitialValue() {
        return 0;
    }

    @Override
    public boolean isUnlimited() {
        return true;
    }

    @Override
    public String getName() {
        return name;
    }
}
