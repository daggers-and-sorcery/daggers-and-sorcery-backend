package com.morethanheroic.swords.attribute.service;

import com.morethanheroic.swords.attribute.domain.GeneralAttribute;
import com.morethanheroic.swords.race.model.modifier.RacialModifier;
import org.springframework.stereotype.Service;

@Service
public class AttributeToRacialModifierConverter {

    public RacialModifier convert(GeneralAttribute generalAttribute) {
        switch (generalAttribute) {
            case STRENGTH:
                return RacialModifier.STRENGTH;
            case ENDURANCE:
                return RacialModifier.ENDURANCE;
            case VITALITY:
                return RacialModifier.VITALITY;
            case INTELLIGENCE:
                return RacialModifier.INTELLIGENCE;
            case WISDOM:
                return RacialModifier.WISDOM;
            case WILLPOWER:
                return RacialModifier.WILLPOWER;
            case PERCEPTION:
                return RacialModifier.PERCEPTION;
            case SWIFTNESS:
                return RacialModifier.SWIFTNESS;
            case DEXTERITY:
                return RacialModifier.DEXTERITY;
            case CHARISMA:
                return RacialModifier.CHARISMA;
            case BEAUTY:
                return RacialModifier.BEAUTY;
        }

        throw new IllegalArgumentException("Bad GeneralAttribute to convert: " + generalAttribute);
    }
}
