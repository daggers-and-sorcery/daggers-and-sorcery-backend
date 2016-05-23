package com.morethanheroic.swords.attribute.domain;

import com.morethanheroic.swords.attribute.domain.type.AttributeType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.WordUtils;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * The {@link AttributeType#COMBAT} attributes.
 */
@RequiredArgsConstructor
public enum CombatAttribute implements Attribute {

    //TODO: Load these values from an xml file and use an CombatAttributeDefinition class.
    MANA(0, false, new GeneralAttribute[]{GeneralAttribute.INTELLIGENCE, GeneralAttribute.WISDOM, GeneralAttribute.WILLPOWER}, 0.5, 0),
    LIFE(15, false, new GeneralAttribute[]{GeneralAttribute.VITALITY, GeneralAttribute.ENDURANCE}, 0.5, 0),
    INITIATION(10, true, new GeneralAttribute[]{GeneralAttribute.SWIFTNESS, GeneralAttribute.PERCEPTION}, 0.5, 20),
    ATTACK(10, true, new GeneralAttribute[]{GeneralAttribute.DEXTERITY, GeneralAttribute.SWIFTNESS}, 0.5, 20),
    MAGIC_ATTACK(10, true, new GeneralAttribute[]{GeneralAttribute.INTELLIGENCE, GeneralAttribute.WISDOM}, 0.5, 20),
    MAGIC_DAMAGE(1, true, new GeneralAttribute[]{GeneralAttribute.WILLPOWER}, 0.25, 3),
    AIMING(10, true, new GeneralAttribute[]{GeneralAttribute.DEXTERITY, GeneralAttribute.PERCEPTION}, 0.5, 20),
    DEFENSE(10, true, new GeneralAttribute[]{GeneralAttribute.ENDURANCE, GeneralAttribute.DEXTERITY}, 0.5, 20),
    SPELL_RESISTANCE(10, true, new GeneralAttribute[]{GeneralAttribute.WILLPOWER, GeneralAttribute.WISDOM}, 0.5, 20),
    DAMAGE(1, true, new GeneralAttribute[]{GeneralAttribute.STRENGTH}, 0.25, 3),
    RANGED_DAMAGE(1, true, new GeneralAttribute[]{GeneralAttribute.DEXTERITY}, 0.25, 3);

    private final int initialValue;
    private final boolean unlimited;
    private final GeneralAttribute[] bonusAttributes;
    private final double bonusPercentage;

    @Getter
    private final int minimalValue;

    @Override
    public AttributeType getAttributeType() {
        return AttributeType.COMBAT;
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
        return WordUtils.capitalize(this.name().toLowerCase().replaceAll("_", " "));
    }

    public List<GeneralAttribute> getBonusAttributes() {
        return Collections.unmodifiableList(Arrays.asList(bonusAttributes));
    }

    public double getBonusPercentage() {
        return bonusPercentage;
    }
}
