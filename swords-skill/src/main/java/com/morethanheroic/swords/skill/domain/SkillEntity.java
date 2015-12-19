package com.morethanheroic.swords.skill.domain;

import com.morethanheroic.swords.skill.repository.dao.SkillDatabaseEntity;
import com.morethanheroic.swords.skill.repository.domain.SkillMapper;
import com.morethanheroic.swords.user.domain.UserEntity;

public class SkillEntity {

    private final UserEntity user;
    private final SkillMapper skillMapper;

    public SkillEntity(UserEntity user, SkillMapper skillMapper) {
        this.user = user;
        this.skillMapper = skillMapper;
    }

    public void addSkillXp(SkillType attribute, long value) {
        switch (attribute) {
            case TWO_HANDED_CRUSHING_WEAPONS:
                skillMapper.addTwoHandedCrushingWeaponsXp(user.getId(), value);
                break;
            case ONE_HANDED_CRUSHING_WEAPONS:
                skillMapper.addOneHandedCrushingWeaponsXp(user.getId(), value);
                break;
            case TWO_HANDED_AXES:
                skillMapper.addTwoHandedAxesXp(user.getId(), value);
                break;
            case ONE_HANDED_AXES:
                skillMapper.addOneHandedAxesXp(user.getId(), value);
                break;
            case THROWING_WEAPONS:
                skillMapper.addThrowingWeaponsXp(user.getId(), value);
                break;
            case FISTFIGHT:
                skillMapper.addFistfightXp(user.getId(), value);
                break;
            case LONGSWORDS:
                skillMapper.addLongswordsXp(user.getId(), value);
                break;
            case SHORTSWORDS:
                skillMapper.addShortswordsXp(user.getId(), value);
                break;
            case POLEARMS:
                skillMapper.addPolearmsXp(user.getId(), value);
                break;
            case DAGGERS:
                skillMapper.addDaggersXp(user.getId(), value);
                break;
            case LONGBOWS:
                skillMapper.addLongbowsXp(user.getId(), value);
                break;
            case SHORTBOWS:
                skillMapper.addShortbowsXp(user.getId(), value);
                break;
            case CROSSBOWS:
                skillMapper.addCrossbowsXp(user.getId(), value);
                break;
            case LIGHT_ARMOR:
                skillMapper.addLightArmorXp(user.getId(), value);
                break;
            case HEAVY_ARMOR:
                skillMapper.addHeavyArmorXp(user.getId(), value);
                break;
            case ROBE_ARMOR:
                skillMapper.addRobeArmorXp(user.getId(), value);
                break;
            case ARMORLESS_DEFENSE:
                skillMapper.addArmorlessDefenseXp(user.getId(), value);
                break;
            case SHIELD_DEFENSE:
                skillMapper.addShieldDefenseXp(user.getId(), value);
                break;
            case STAFF:
                skillMapper.addStaffsXp(user.getId(), value);
                break;
            case WAND:
                skillMapper.addWandsXp(user.getId(), value);
                break;
            case SPECTRE:
                skillMapper.addSpectresXp(user.getId(), value);
                break;
            case SCAVENGING:
                skillMapper.addScavengingXp(user.getId(), value);
                break;
            case COOKING:
                skillMapper.addCookingXp(user.getId(), value);
                break;
            default:
                throw new IllegalArgumentException("Unknown attribute: " + attribute);
        }
    }

    public int getSkillXp(SkillType attribute) {
        SkillDatabaseEntity skills = skillMapper.getSkills(user.getId());

        switch (attribute) {
            case TWO_HANDED_CRUSHING_WEAPONS:
                return skills.getTwoHandedCrushingWeaponsXp();
            case ONE_HANDED_CRUSHING_WEAPONS:
                return skills.getOneHandedCrushingWeaponsXp();
            case TWO_HANDED_AXES:
                return skills.getTwoHandedAxesXp();
            case ONE_HANDED_AXES:
                return skills.getOneHandedAxesXp();
            case THROWING_WEAPONS:
                return skills.getThrowingWeaponsXp();
            case FISTFIGHT:
                return skills.getFistfightXp();
            case LONGSWORDS:
                return skills.getLongswordsXp();
            case SHORTSWORDS:
                return skills.getShortswordsXp();
            case POLEARMS:
                return skills.getPolearmsXp();
            case DAGGERS:
                return skills.getDaggersXp();
            case LONGBOWS:
                return skills.getLongswordsXp();
            case SHORTBOWS:
                return skills.getShowrtbowsXp();
            case CROSSBOWS:
                return skills.getCrossbowsXp();
            case LIGHT_ARMOR:
                return skills.getLightArmorXp();
            case HEAVY_ARMOR:
                return skills.getHeavyArmorXp();
            case ROBE_ARMOR:
                return skills.getRobeArmorXp();
            case ARMORLESS_DEFENSE:
                return skills.getArmorlessDefenseXp();
            case SHIELD_DEFENSE:
                return skills.getShieldDefenseXp();
            case STAFF:
                return skills.getStaffsXp();
            case WAND:
                return skills.getWandsXp();
            case SPECTRE:
                return skills.getSpectresXp();
            case SCAVENGING:
                return skills.getSpectresXp();
            case COOKING:
                return skills.getCookingXp();
            default:
                throw new IllegalArgumentException("Unknown attribute: " + attribute);
        }
    }

    public int getSkillLevel(SkillType attribute) {
        return getSkillLevelFromXp(getSkillXp(attribute));
    }

    public long getSkillXpToNextLevel(SkillType attribute) {
        return getSkillXpFromLevel(getSkillLevel(attribute) + 1);
    }

    public long getSkillXpBetweenNextLevel(SkillType attribute) {
        return getSkillXpFromLevel(getSkillLevel(attribute) + 1) - getSkillXpFromLevel(getSkillLevel(attribute));
    }

    public long getSkillXpFromLevel(int level) {
        if (level < 1) {
            return 0;
        }

        return (long) Math.ceil((Math.pow((double) level, (double) 2) * (((double) level * (double) level) / (double) 4) + (double) 60) / (double) 2);
    }

    public int getSkillLevelFromXp(long xp) {
        if (xp < 32) {
            return 1;
        }

        return (int) Math.floor(Math.pow((double) 8 * (double) xp - (double) 240, 0.25));
    }
}
