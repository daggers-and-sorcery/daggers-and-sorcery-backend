package com.morethanheroic.swords.attribute.service.calc.type;

import com.morethanheroic.swords.attribute.domain.SkillAttribute;
import com.morethanheroic.swords.item.domain.ItemType;
import com.morethanheroic.swords.skill.domain.SkillGroup;
import com.morethanheroic.swords.skill.domain.SkillType;
import org.springframework.stereotype.Service;

import java.util.*;

//TODO: load these maps from config xml files.
@Service
public class SkillTypeCalculator {

    private final Map<SkillGroup, List<SkillType>> skillsByType;

    public SkillTypeCalculator() {
        final Map<SkillGroup, List<SkillType>> skillsByTypeResult = new HashMap<>();
        for (SkillType skillType : SkillType.values()) {
            final SkillGroup skillGroupOfSkillType = skillType.getSkillGroup();

            if (!skillsByTypeResult.containsKey(skillGroupOfSkillType)) {
                skillsByTypeResult.put(skillGroupOfSkillType, new ArrayList<>());
            }

            skillsByTypeResult.get(skillGroupOfSkillType).add(skillType);
        }

        for(SkillGroup skillGroup : skillsByTypeResult.keySet()) {
            skillsByTypeResult.put(skillGroup, Collections.unmodifiableList(skillsByTypeResult.get(skillGroup)));
        }

        skillsByType = Collections.unmodifiableMap(skillsByTypeResult);
    }

    public List<SkillType> getSkillsByGroup(final SkillGroup skillGroup) {
        return skillsByType.get(skillGroup);
    }

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
                return SkillType.LONGSWORDS;
            case SHORTSWORDS:
                return SkillType.SHORTSWORDS;
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
            case FOCUS:
                return SkillType.FOCUS;
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
                return SkillType.LONGSWORDS;
            case SHORTSWORDS:
                return SkillType.SHORTSWORDS;
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
            case COOKING:
                return SkillType.COOKING;
            case FISTFIGHT:
                return SkillType.FISTFIGHT;
            case SCAVENGING:
                return SkillType.SCAVENGING;
            case LEATHERWORKING:
                return SkillType.LEATHERWORKING;
            case SMITHING:
                return SkillType.SMITHING;
            case FOCUS:
                return SkillType.FOCUS;
            case DESTRUCTION:
                return SkillType.DESTRUCTION;
            case RESTORATION:
                return SkillType.RESTORATION;
            case ALTERATION:
                return SkillType.ALTERATION;
            case LOCKPICKING:
                return SkillType.LOCKPICKING;
            default:
                throw new IllegalArgumentException("No skill found for skill attribute type: " + skillAttribute);
        }
    }

    public SkillAttribute getSkillAttributeFromSkillType(SkillType skillType) {
        switch (skillType) {
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
                return SkillAttribute.LONGSWORDS;
            case SHORTSWORDS:
                return SkillAttribute.SHORTSWORDS;
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
            case SHIELD_DEFENSE:
                return SkillAttribute.SHIELD_DEFENSE;
            case STAFF:
                return SkillAttribute.STAFF;
            case WAND:
                return SkillAttribute.WAND;
            case SPECTRE:
                return SkillAttribute.SPECTRE;
            case ARMORLESS_DEFENSE:
                return SkillAttribute.ARMORLESS_DEFENSE;
            case COOKING:
                return SkillAttribute.COOKING;
            case FISTFIGHT:
                return SkillAttribute.FISTFIGHT;
            case SCAVENGING:
                return SkillAttribute.SCAVENGING;
            case LEATHERWORKING:
                return SkillAttribute.LEATHERWORKING;
            case SMITHING:
                return SkillAttribute.SMITHING;
            case FOCUS:
                return SkillAttribute.FOCUS;
            case DESTRUCTION:
                return SkillAttribute.DESTRUCTION;
            case RESTORATION:
                return SkillAttribute.RESTORATION;
            case ALTERATION:
                return SkillAttribute.ALTERATION;
            case LOCKPICKING:
                return SkillAttribute.LOCKPICKING;
            default:
                throw new IllegalArgumentException("No skill type found for attribute type: " + skillType);
        }
    }
}
