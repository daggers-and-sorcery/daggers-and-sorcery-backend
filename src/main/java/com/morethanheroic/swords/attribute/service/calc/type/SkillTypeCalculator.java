package com.morethanheroic.swords.attribute.service.calc.type;

import com.morethanheroic.swords.attribute.domain.SkillAttribute;
import com.morethanheroic.swords.item.domain.ItemType;
import org.springframework.stereotype.Service;

@Service
public class SkillTypeCalculator {

    public SkillAttribute getSkillFromItemType(ItemType itemType) {
        switch (itemType) {
            case TWO_HANDED_CRUSHING_WEAPONS:
                return SkillAttribute.TWO_HANDED_CRUSHING_WEAPONS;
            case ONE_HANDED_CRUSHING_WEAPONS:
                return SkillAttribute.ONE_HANDED_CRUSHING_WEAPONS;
            case TWO_HANDED_AXES:
                return SkillAttribute.TWO_HANDED_AXES;
            case ONE_HANDED_AXES:
                return SkillAttribute.ONE_HANDED_AXES;
            case THROWING_WEAPONS:
                return SkillAttribute.THROWING_WEAPONS;
            case LONGSWORDS:
                return SkillAttribute.LONGBOWS;
            case SHORTSWORDS:
                return SkillAttribute.SHORTBOWS;
            case POLEARMS:
                return SkillAttribute.POLEARMS;
            case DAGGERS:
                return SkillAttribute.DAGGERS;
            case LONGBOWS:
                return SkillAttribute.LONGBOWS;
            case SHORTBOWS:
                return SkillAttribute.SHORTBOWS;
            case CROSSBOWS:
                return SkillAttribute.CROSSBOWS;
            case LIGHT_ARMOR:
                return SkillAttribute.LIGHT_ARMOR;
            case HEAVY_ARMOR:
                return SkillAttribute.HEAVY_ARMOR;
            case ROBE_ARMOR:
                return SkillAttribute.ROBE_ARMOR;
            case SHIELD:
                return SkillAttribute.SHIELD_DEFENSE;
            case STAFF:
                return SkillAttribute.STAFF;
            case WAND:
                return SkillAttribute.WAND;
            case SPECTRE:
                return SkillAttribute.SPECTRE;
            default:
                throw new IllegalArgumentException("No skill found for item type: " + itemType);
        }
    }
}
