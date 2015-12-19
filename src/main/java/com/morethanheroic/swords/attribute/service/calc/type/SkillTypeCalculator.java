package com.morethanheroic.swords.attribute.service.calc.type;

import com.morethanheroic.swords.attribute.domain.SkillAttribute;
import com.morethanheroic.swords.item.domain.ItemType;
import com.morethanheroic.swords.skill.domain.SkillType;
import org.springframework.stereotype.Service;

//TODO: load these maps from config xml files.
@Service
public class SkillTypeCalculator {

    public SkillType getSkillFromItemType(ItemType itemType) {
        switch (itemType) {
            case TWO_HANDED_CRUSHING_WEAPONS:
                return SkillType.TWO_HANDED_CRUSHING_WEAPONS;
            case ONE_HANDED_CRUSHING_WEAPONS:
                return SkillType.ONE_HANDED_CRUSHING_WEAPONS;
            case TWO_HANDED_AXES:
                return SkillType.TWO_HANDED_AXES;
            case ONE_HANDED_AXES:
                return SkillType.ONE_HANDED_AXES;
            case THROWING_WEAPONS:
                return SkillType.THROWING_WEAPONS;
            case LONGSWORDS:
                return SkillType.LONGBOWS;
            case SHORTSWORDS:
                return SkillType.SHORTBOWS;
            case POLEARMS:
                return SkillType.POLEARMS;
            case DAGGERS:
                return SkillType.DAGGERS;
            case LONGBOWS:
                return SkillType.LONGBOWS;
            case SHORTBOWS:
                return SkillType.SHORTBOWS;
            case CROSSBOWS:
                return SkillType.CROSSBOWS;
            case LIGHT_ARMOR:
                return SkillType.LIGHT_ARMOR;
            case HEAVY_ARMOR:
                return SkillType.HEAVY_ARMOR;
            case ROBE_ARMOR:
                return SkillType.ROBE_ARMOR;
            case SHIELD:
                return SkillType.SHIELD_DEFENSE;
            case STAFF:
                return SkillType.STAFF;
            case WAND:
                return SkillType.WAND;
            case SPECTRE:
                return SkillType.SPECTRE;
            default:
                throw new IllegalArgumentException("No skill found for item type: " + itemType);
        }
    }

    public SkillType getSkillTypeFromSkillAttribute(SkillAttribute skillAttribute) {
        switch (skillAttribute) {
            case TWO_HANDED_CRUSHING_WEAPONS:
                return SkillType.TWO_HANDED_CRUSHING_WEAPONS;
            case ONE_HANDED_CRUSHING_WEAPONS:
                return SkillType.ONE_HANDED_CRUSHING_WEAPONS;
            case TWO_HANDED_AXES:
                return SkillType.TWO_HANDED_AXES;
            case ONE_HANDED_AXES:
                return SkillType.ONE_HANDED_AXES;
            case THROWING_WEAPONS:
                return SkillType.THROWING_WEAPONS;
            case LONGSWORDS:
                return SkillType.LONGBOWS;
            case SHORTSWORDS:
                return SkillType.SHORTBOWS;
            case POLEARMS:
                return SkillType.POLEARMS;
            case DAGGERS:
                return SkillType.DAGGERS;
            case LONGBOWS:
                return SkillType.LONGBOWS;
            case SHORTBOWS:
                return SkillType.SHORTBOWS;
            case CROSSBOWS:
                return SkillType.CROSSBOWS;
            case LIGHT_ARMOR:
                return SkillType.LIGHT_ARMOR;
            case HEAVY_ARMOR:
                return SkillType.HEAVY_ARMOR;
            case ROBE_ARMOR:
                return SkillType.ROBE_ARMOR;
            case SHIELD_DEFENSE:
                return SkillType.SHIELD_DEFENSE;
            case STAFF:
                return SkillType.STAFF;
            case WAND:
                return SkillType.WAND;
            case SPECTRE:
                return SkillType.SPECTRE;
            case ARMORLESS_DEFENSE:
                return SkillType.ARMORLESS_DEFENSE;
            default:
                throw new IllegalArgumentException("No skill found for skill attribute type: " + skillAttribute);
        }
    }
}
