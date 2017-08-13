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

        for (SkillGroup skillGroup : skillsByTypeResult.keySet()) {
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
            case SCEPTRE:
                return SkillType.SCEPTRE;
            case FOCUS:
                return SkillType.FOCUS;
            default:
                throw new IllegalArgumentException("No skill found for item type: " + itemType);
        }
    }

    public SkillType getSkillTypeFromSkillAttribute(SkillAttribute skillAttribute) {
        return SkillType.valueOf(skillAttribute.name());
    }

    public SkillAttribute getSkillAttributeFromSkillType(SkillType skillType) {
        return SkillAttribute.valueOf(skillType.name());
    }
}
